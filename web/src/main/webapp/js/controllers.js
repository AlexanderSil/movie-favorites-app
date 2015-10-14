var angularApp = angular.module("angularApp", ["angularApp.services", "angularApp.controllers",
                                            "ngResource", "ngRoute", "ngCookies"]);

angularApp.config(["$routeProvider", "$locationProvider", "$httpProvider",
    function ($routeProvider, $locationProvider, $httpProvider) {

        $routeProvider.when("/search", {
            templateUrl: "partials/search.html",
            controller: "SearchController"
        })
        .when("/favorites", {
            templateUrl: "partials/favorites.html",
            controller: "FavoritesController"
        })
        .when("/favoriteMovies", {
            templateUrl: "partials/favoriteMovies.html",
            controller: "FavoriteMoviesController"
        })
        .otherwise({
                      redirectTo: "/favorites"
        });
    }]);

var angularController = angular.module("angularApp.controllers", []);

angularController.controller("MainController", function ($scope) {
})

angularController.controller("SearchController", function ($scope, MoviesService, FavoritesService) {
    $scope.moviesList = [];
    $scope.maxResult = 10;
    $scope.search = function() {
            var query = $scope.query;
            var maxResult = $scope.maxResult;
            if (query != null) {
                $scope.moviesList = MoviesService.search(query, maxResult, $scope);
            }
        }
    $scope.favoritesList = FavoritesService.getAll($scope);

    $scope.selectFavorite = function (idFavorite) {
        $scope.idFavorite = idFavorite;
    }
    $scope.addToFavorite = function(idMovie) {
        var idMovie = idMovie;
        $('#addMovieToFavorite').modal({
        			 onApprove : function() {
        			    MoviesService.addMovieToFavorites(idMovie, $scope.idFavorite);
        		     }
        		}).modal('show');
    }
});

angularController.controller("FavoritesController", function ($scope, FavoritesService, MoviesService) {
    $scope.favoritesList = FavoritesService.getAll($scope);
    $scope.showFavoriteMovies = function(favoriteId) {
        MoviesService.showFavoriteMovies(favoriteId);
    }

    $scope.createFavorite = function() {
        var idMovie = idMovie;
        $('#createFavorite').modal({
        			 onApprove : function() {
        			    $scope.FavoriteTitle;
        			    FavoritesService.saveFavorite($scope.FavoriteTitle, $scope);
        		     }
        		}).modal('show');
    }
});

angularController.controller("FavoriteMoviesController", function ($scope) {
});