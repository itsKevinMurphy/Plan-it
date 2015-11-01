//Angular service to enable HTTP requests for Events API
angular.module('services', [])
.service("ServiceForEvents" , ['$http', function ($http) {
    this.addEvent = function(data){
      return $http({url:'http://localhost:3000/events', method: "POST", data: data});
      }
}
]);
