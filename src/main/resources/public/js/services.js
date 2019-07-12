angular.module('app.services', []).factory('Article', function($resource) {
  return $resource('/api/v1/articles/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
