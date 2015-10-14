var angularService = angular.module("angularApp.services", []);


angularService.factory("MoviesService", function($http, $rootScope, $cookieStore, $location) {
    var moviesService = {

        search: function(query, maxResult, $scope) {
            var promise = $http({
                url: 'api/movie/search',
                method: 'GET',
                params: {"query" : query, "maxResultStr" : maxResult}
            }).then(function(response){
                $scope.moviesList = response.data;
                return response.data;
            });
            return promise;
        },
        showFavoriteMovies: function(favoriteId) {
            var promise = $http({
                            url: 'api/movie/getMovies',
                            method: 'GET',
                            params: {"favoriteId" : favoriteId}
                        }).then(function(response){
                            $rootScope.favoriteMoviesList = response.data;
                            $location.path("/favoriteMovies");
                        });
        },
        addMovieToFavorites: function(movieId, favoriteId) {
            var promise = $http({
                                        url: 'api/movie/saveMovie',
                                        method: 'POST',
                                        params: {"movieId" : movieId, "favoriteId" : favoriteId}
                                    });
        }
    };

    return moviesService;
});

angularService.factory("FavoritesService", function($http, $rootScope, $cookieStore, $location) {
    var favoritesService = {

        getAll: function($scope) {
            var promise = $http({
                url: 'api/favorites/getAll',
                method: 'GET'
            }).then(function(response){
                $scope.favoritesList = response.data;
                return response.data;
            });
        },
        saveFavorite: function(title, $scope) {
            var promise = $http({
                            url: 'api/favorites/saveFavorite',
                            method: 'POST',
                            params: {"title" : title}
                        }).then(function(response){
                            $scope.favoritesList.push(response.data);
                        });
        }
    };

    return favoritesService;
});