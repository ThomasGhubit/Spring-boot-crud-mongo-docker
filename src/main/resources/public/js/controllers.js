angular.module('app.controllers', []).controller('TaskListController', function($scope, $state, popupService, $window, Task) {
  $scope.tasks = Task.query(); //fetch all tasks. Issues a GET to /tasks

  $scope.deleteTask = function(task) { // Delete a Task. Issues a DELETE to /tasks/:id
    if (popupService.showPopup('Already complete this?')) {
      task.$delete(function() {
        $scope.tasks = Task.query(); 
        $state.go('tasks');
      });
    }
  };
}).controller('TaskViewController', function($scope, $stateParams, Task) {
  $scope.task = Task.get({ id: $stateParams.id }); //Get a single task.Issues a GET to /tasks/:id
}).controller('TaskCreateController', function($scope, $state, $stateParams, Task) {
  $scope.task = new Task();  //create new task instance. Properties will be set via ng-model on UI

  $scope.addTask = function() { //create a new task. Issues a POST to /tasks
    $scope.task.$save(function() {
      $state.go('tasks'); // on success go back to the list i.e. tasks state.
    });
  };
}).controller('TaskEditController', function($scope, $state, $stateParams, Task) {
  $scope.updateTask = function() { //Update the edited task. Issues a PUT to /tasks/:id
    $scope.task.$update(function() {
      $state.go('tasks'); // on success go back to the list i.e. tasks state.
    });
  };

  $scope.loadTask = function() { //Issues a GET request to /tasks/:id to get a task to update
    $scope.task = Task.get({ id: $stateParams.id });
  };

  $scope.loadTask(); // Load a task which can be edited on UI
});
