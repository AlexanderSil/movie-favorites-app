package com.movie.favorite.dao.impl;

import com.movie.favorite.dao.api.BasicDao;
import com.movie.favorite.domain.BasicObject;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

public class BasicJpaDao<T extends BasicObject> implements BasicDao<T> {

    private EntityManager entityManager;

    private final Class<T> type;

    public BasicJpaDao(final Class<T> aType) {
        this.type = aType;
    }

    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @Transactional
    public void delete(final T entity) {
        final EntityManager em = getEntityManager();

        if (em.contains(entity)) {
            em.remove(entity);
        } else {
            final BasicObject reference =
                    em.getReference(entity.getClass(), entity.getId());
            em.remove(reference);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<T> findAll() {
        String selectQuery = "Select obj from " + this.type.getSimpleName()
                + " obj ORDER BY obj.id";
        Query query = this.getEntityManager().createQuery(selectQuery);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(final Long id) {
        return this.entityManager.find(this.type, id);
    }


    @Override
    @Transactional
    public T saveOrUpdate(final T entity) {
        T result;
        try {
            if (entity.getId() == null) {
                this.entityManager.persist(entity);
                result = entity;
            } else {
                result = this.entityManager.merge(entity);
            }
            entityManager.flush();
            return result;
        } catch (EntityExistsException ex) {
            throw new RuntimeException(ex);
        }

    }
}
