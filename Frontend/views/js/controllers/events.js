angular.module('eventController', [])
.controller('CreateEventController', function ($scope, $location, ServiceForEvents){
  //Create a scope to determine if form has been submitted
  $scope.submitted = false;

//Method called when the form is submitted
$scope.createEvent = function(){
  console.log($scope.event);
    //If the form fields are all valid
  if ($scope.new_event_form.$valid) {
    console.log($scope.new_event_form);
    //Call the addEvent method of Event Service to create a new event.
  ServiceForEvents.addEvent($scope.event).success(function (data)
  {
    console.log("Event Created.")
  });
}
else {
    $scope.new_event_form.submitted = true;
}
}

});
