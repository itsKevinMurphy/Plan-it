// var app = angular.module('planItApp', ['ngRoute']);
var app = angular.module('planItApp', ['ui.router']);

//to route views on single page
app.config(['$stateProvider',
  function ($stateProvider) {
    $stateProvider
        .state('event', {
            url: '/event',
            views: {
                'generalView': {
                    templateUrl: 'event.html'
                }
            }
        })
        .state('event.details', {
            url: '',
            views: {
                'rightView': {
                    templateUrl: 'event.details.html'
                }
            }
        })
        .state('event.chat', {
            url: '',
            views: {
                'rightView': {
                    templateUrl: 'event.chat.html'
                }
            }
        })
        .state('event.items', {
            url: '',
            views: {
                'rightView': {
                    templateUrl: 'event.items.html'
                }
            }
        })
        .state('event.budget', {
            url: '',
            views: {
                'rightView': {
                    templateUrl: 'event.budget.html'
                }
            }
        })
        .state('event.create', {
            url: '',
            views: {
                'rightView': {
                    templateUrl: 'event.create.html'
                }
            }
        })
        .state('contact', {
            url: '/contact',
            views: {
                'generalView': {
                    templateUrl: 'contact.html'
                }
            }
        })
    
    }]);

//to extract socket object to be used in controller
app.factory('socket', function(){
  return io.connect('http://localhost:4030');
});
