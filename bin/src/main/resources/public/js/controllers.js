angular.module('app.controllers', []).controller('ArticleListController', function($scope, $state, popupService, $window, Article) {
  $scope.articles = Article.query(); //fetch all articles. Issues a GET to /articles

  $scope.deleteArticle = function(article) { // Delete a Article. Issues a DELETE to /articles/:id
    if (popupService.showPopup('Really delete this?')) {
      article.$delete(function() {
        $scope.articles = Article.query(); 
        $state.go('articles');
      });
    }
  };
}).controller('ArticleViewController', function($scope, $stateParams, Article) {
  $scope.article = Article.get({ id: $stateParams.id }); //Get a single article.Issues a GET to /articles/:id
}).controller('ArticleCreateController', function($scope, $state, $stateParams, Article) {
  $scope.article = new Article();  //create new article instance. Properties will be set via ng-model on UI

  $scope.addArticle = function() { //create a new article. Issues a POST to /articles
    $scope.article.$save(function() {
      $state.go('articles'); // on success go back to the list i.e. articles state.
    });
  };
}).controller('ArticleEditController', function($scope, $state, $stateParams, Article) {
  $scope.updateArticle = function() { //Update the edited article. Issues a PUT to /articles/:id
    $scope.article.$update(function() {
      $state.go('articles'); // on success go back to the list i.e. articles state.
    });
  };

  $scope.loadArticle = function() { //Issues a GET request to /articles/:id to get a article to update
    $scope.article = Article.get({ id: $stateParams.id });
  };

  $scope.loadArticle(); // Load a article which can be edited on UI
});
