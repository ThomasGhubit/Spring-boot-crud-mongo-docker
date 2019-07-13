angular.module('app.services', []).factory('Task', function($resource) {
  return $resource('/api/v1/tasks/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
