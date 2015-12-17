angular.module('controller', ['angularMoment'])
.controller('MainController', function($scope, ServiceForUser, $cookies, $location)
{
  console.log('Hello from Main controller');

  $scope.token = $cookies.token;
  $scope.isLogged = false;

  if($scope.token != "")
  {
    $scope.isLogged = true;

  }

  $scope.checkForUrl = function()
  {
    if($scope.isLogged == true)
    {
      $location.path('/event');
    }
    else
    {
      $location.path('/')
    }
  }

  $scope.logout = function()
  {
    ServiceForUser.logoutUser();
    $scope.isLogged = false;
  }
})
.controller('ParentController', function($scope, ServiceForUser, $location){
  $scope.token;
  $scope.currentUserID;
  $scope.submitted = false;
  console.log("Hello from ParentController");

  $scope.loginUser = function(){
    console.log("In Login User method.");

    if ($scope.login_form.$valid) {

    ServiceForUser.loginUser($scope.user).success(function(data){
      console.log(data);
      ServiceForUser.setToken(data.token);
      ServiceForUser.setUser(data.userID);
      $scope.$parent.isLogged = true;
      $location.path('/event');
    });
    }
    else {
            $scope.login_form.submitted = true;
            console.log($scope.login_form);
            console.log($scope.submitted);
        }

  }

})
.controller('CreateEventController', function ($window, $scope, ServiceForEvents, ServiceForUser){
  //Retrive token from service.
  $scope.token = ServiceForUser.getToken();
  console.log("Token :" + $scope.token)

  //Create a scope to determine if form has been submitted
  $scope.submitted = false;

  $scope.imageUpload = function(event){
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = $scope.imageIsLoaded;
    reader.readAsDataURL(file);
  }
  $scope.imageIsLoaded = function(e){
    $scope.$apply(function() {
      $scope.step = e.target.result;
      // console.log(e.target.result);
    });
   }

  //Method called when the form is submitted
  $scope.createEvent = function(){

      if($scope.step != null && $scope.event)
      {
        console.log($scope.event.picture);
        $scope.event.picture =  $scope.step.replace('data:image/jpeg;base64,', '');
        console.log($scope.event.picture);
      }


    console.log($scope.event);
      //If the form fields are all valid
    if ($scope.new_event_form.$valid)
    {
      console.log($scope.new_event_form);
      //Call the addEvent method of Event Service to create a new event.
      ServiceForEvents.addEvent($scope.event, $scope.token).success(function (data)
      {
        console.log("Event Created.")
        $window.location.reload();
      });
    }
    else {
        $scope.new_event_form.submitted = true;
    }
  }

})

.controller('UpdateEventController', function ($window, $scope, $location, $stateParams, ServiceForEvents, ServiceForUser){
  $scope.event = [];
  $scope.id = $stateParams.eventID;
  $scope.submitted = false;
  $scope.token = ServiceForUser.getToken();

  console.log($scope.id);
  //Retrieve selected Event info based on the id
  ServiceForEvents.getEventById($scope.id, $scope.token).success(function (data) {
      //Store the event details in the event model
      $scope.event = data;
  });

  console.log($scope.event);

  $scope.updateEvent = function () {
    if($scope.step != null && $scope.event)
        {
          console.log("Bfore" + $scope.event.picture);
          $scope.event.picture =  $scope.step.replace('data:image/jpeg;base64,', '');
          console.log("After" + $scope.event.picture);
        }
    if ($scope.update_event_form.$valid) {
      // Submit as normal
      console.log($scope.update_event_form);
      ServiceForEvents.updateEvent($scope.id, $scope.event, $scope.token).success(function (data)
      {
        console.log($scope.event);
        // $window.location.reload();
        //should redirect to events
        $location.path('/event/eventinfo/'+$scope.id);
      });
    } else {
        $scope.update_event_form.submitted = true;
        console.log("Form is not complete.")
    }
  }

  $scope.imageUpload = function(event){
    var file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = $scope.imageIsLoaded;
    reader.readAsDataURL(file);
  }
  $scope.imageIsLoaded = function(e){
    $scope.$apply(function() {
      $scope.step = e.target.result;
      // console.log(e.target.result);
    });
  }

})
.controller('EventInviteFriendController', function ($window, $scope, $stateParams, ServiceForEvents, ServiceForUser){

  $scope.token = ServiceForUser.getToken();
  $scope.id = $stateParams.eventID;
  $scope.currentUserID = ServiceForUser.getUser();
  $scope.friendList;
  $scope.eventNumber;
  console.log("my id: " + $scope.currentUserID);

    ServiceForUser.getAllFriends($scope.currentUserID, $scope.token).success(function (data)
  {
      $scope.friendList = data;
      console.log(data);
  }
  );

  $scope.inviteFriend = function(friendID)
  {
    ServiceForUser.inviteEventMember($scope.id, friendID, $scope.token).success(function(data)
    {
      console.log("Friend Invited.")
    }
    ).error (function(error) {
      $scope.errorMsg = "Conflict error: " + error;
      console.log(error);
    });
  }
})

.controller('EventListController', function ($scope, ServiceForEvents, ServiceForUser, $location){
  $scope.token = ServiceForUser.getToken();
  $scope.eventFilter = "";
  console.log($scope.token);

  ServiceForEvents.getAllEvents($scope.token).success(function (data)
  {
      $scope.eventList = data;
      console.log(data);
  }
  );

  $scope.clearSearch = function()
  {
    $scope.searchFilter = null;
    $scope.searchword = "";
  }

  $scope.filterForOwner = function()
  {
    $scope.clearSearch();
    console.log("My Events.");
    $scope.eventFilter = "Owner";
  }
  $scope.filterForAttending = function()
  {
    $scope.clearSearch();
    console.log("My Events.");
    $scope.eventFilter = "Attending";
  }
  $scope.filterForInvited = function()
  {
    $scope.clearSearch();
    console.log("My Events.");
    $scope.eventFilter = "Invited";
  }
  $scope.filterForLeft = function()
  {
    $scope.clearSearch();
    console.log("My Events.");
    $scope.eventFilter = "Declined";
  }
  $scope.filterForAll = function()
  {
    $scope.clearSearch();
    console.log("My Events.");
    $scope.eventFilter = "";
  }
  $scope.searchEvents = function()
  {
    $scope.eventFilter = "";
    $scope.searchFilter = $scope.searchword;
  }

  $scope.setEventID = function(eventID)
  {
    ServiceForEvents.setEvent(eventID);
    console.log("Set Event ID: " + eventID)
  }

  $scope.getEventDetails = function()
  {
    $scope.eventNumber = ServiceForEvents.getEvent();
    console.log("Event ID is:" + $scope.eventNumber);
  }

  $scope.checkOwner = function (toCheck) {
    if (toCheck.isAttending == "Owner") {
      return true;
    }
  }
  $scope.checkInvited = function (toCheck) {
    if (toCheck.isAttending == "Invited") {
      return true;
    }
  }
  $scope.checkAttending = function (toCheck) {
    if (toCheck.isAttending == "Attending") {
      return true;
    }
  }
  $scope.checkDeclined = function (toCheck) {
    if (toCheck.isAttending == "Declined") {
      return true;
    }
  }
  $scope.checkLeft = function (toCheck) {
    if (toCheck.isAttending == "Left") {
      return true;
    }
  }

  $scope.checkEventID = function()
  {
    if(ServiceForEvents.getEvent() != "")
    {
      return true;
    }
    else {
      return false;
    }
  }

  $scope.checkInvitedOrDeclined = function()
  {
    if(ServiceForEvents.getEventStatus() == "Invited" || ServiceForEvents.getEventStatus() == "Declined")
    {
      return false;
    }
    else {
      return true;
    }
  }

})

.controller('EventDetailsController', function ($window, $location, $scope, $stateParams, ServiceForEvents, ServiceForUser){
  $scope.id = $stateParams.eventID;
  ServiceForEvents.setEvent($scope.id);
  $scope.token = ServiceForUser.getToken();

  console.log($scope.id);
  ServiceForEvents.getEventById($scope.id, $scope.token).success(function (data)
  {
    console.log(data);
    $scope.event = data;
    ServiceForEvents.setEventStatus($scope.event.isAttending);
    $scope.event.thumbnail = 'data:image/jpeg;base64,' + $scope.event.picture;
  }
  );

  ServiceForEvents.getMembersByEventId($scope.id, $scope.token).success(function (data)
  {
    console.log(data);
    $scope.memberList = data;
  }
  );

  $scope.showAcceptButton = function (toCheck) {
    if ((toCheck == "Invited") || (toCheck == "Declined")) {
      return true;
    }
  }
  $scope.showLeaveButton = function (toCheck) {
    if ((toCheck != "Invited") && (toCheck != "Owner")) {
      return true;
    }
  }
  $scope.checkOwner = function (toCheck) {
    if (toCheck == "Owner") {
      return true;
    }
  }
  $scope.checkInvited = function (toCheck) {
    if (toCheck == "Invited") {
      return true;
    }
  }
  $scope.checkAttending = function (toCheck) {
    if (toCheck == "Attending") {
      return true;
    }
  }
  $scope.checkDeclined = function (toCheck) {
    if (toCheck == "Declined") {
      return true;
    }
  }
  $scope.checkLeft = function (toCheck) {
    if (toCheck == "Left") {
      return true;
    }
  }

  //$scope.event.picture = "data:image/jpeg;base64," + $scope.event.picture;

  $scope.deleteEvent = function () {
    $scope.token = ServiceForUser.getToken();

    console.log("about to delete event")
    if (confirm("Are you sure you want to delete the event?") == true)
        ServiceForEvents.deleteEvent($scope.id, $scope.token).success(function (data) {});
    $window.location.reload();
    $location.path('event');
  };
  $scope.leaveEvent = function () {
    $scope.token = ServiceForUser.getToken();

    console.log("about to leave event")
    if (confirm("Are you sure you want to leave the event?") == true)
        ServiceForEvents.leaveEvent($scope.id, $scope.token).success(function (data) {});
    $window.location.reload();
    $location.path('event');
  };

  $scope.attendEvent = function () {
    $scope.token = ServiceForUser.getToken();

    console.log("about to attend event invitation")
    ServiceForEvents.answerInvitation($scope.id, "Attending", $scope.token).success(function (data) {});
    $window.location.reload();
    $location.path('event');
  };
  $scope.declineEvent = function () {
    $scope.token = ServiceForUser.getToken();

    console.log("about to decline event invitation")
    ServiceForEvents.answerInvitation($scope.id, "Declined", $scope.token).success(function (data) {});
    $window.location.reload();
    $location.path('event');
  };


})

// .controller('InviteFriendController')


//search users
.controller('SearchUserController', function ($scope, ServiceForUser){
  $scope.token = ServiceForUser.getToken();
  console.log($scope.token);

  $scope.searchUser = function () {
    console.log($scope.user.search);
    ServiceForUser.searchUser($scope.user.search, $scope.token).success(function (data)
    {
        $scope.user = data;
        console.log(data);
    }
    );
  }

})

//user profile
.controller('UserProfileController', function ($scope, $window, $location, $stateParams, ServiceForUser){
  $scope.id = $stateParams.userID;
  $scope.token = ServiceForUser.getToken();
  $scope.currentUserID = ServiceForUser.getUser();
  console.log($scope.id);
  $scope.friend = {};
  $scope.friend.userID;

  ServiceForUser.findUserByID($scope.id, $scope.token).success(function (data)
  {
      $scope.user = data;
      $scope.friend.userID = data.UserID;
      console.log($scope.friend);
      console.log(data);
  }
  );

  $scope.addNewFriend = function () {
    $scope.token = ServiceForUser.getToken();

    console.log("my id: " + $scope.currentUserID + " | friend id: " + $scope.friend);
    console.log("attempting to add " + $scope.friend.userID);
    ServiceForUser.addNewFriend($scope.currentUserID, $scope.friend, $scope.token).success(function (data) {
      console.log("successfully added " + $scope.friend.userID);
    });
    $window.location.reload();
    $location.path('/friends');
  };

  $scope.removeFriend = function () {
    $scope.token = ServiceForUser.getToken();

    console.log("my id: " + $scope.currentUserID + " | friend id: " + $scope.friend);
    console.log("attempting to remove " + $scope.friend.userID);
    if (confirm("Are you sure you want to remove friend?") == true)
        ServiceForUser.removeFriend($scope.currentUserID, $scope.friend.userID, $scope.token).success(function (data) {
          console.log("successfully removed " + $scope.friend.userID);
        });
    $window.location.reload();
    $location.path('/friends');
  };


})

//view friends
.controller('ViewFriendsController', function ($scope, ServiceForUser){
  $scope.token = ServiceForUser.getToken();
  // $scope.id = $stateParams.myID;
  $scope.currentUserID = ServiceForUser.getUser();
  console.log("my id: " + $scope.currentUserID);

    ServiceForUser.getAllFriends($scope.currentUserID, $scope.token).success(function (data)
  {
      $scope.friendList = data;
      console.log(data);
  }
  );

})


.controller('ItemListController', function ($scope, $window, ServiceForItems, ServiceForEvents, ServiceForUser){
  $scope.token = ServiceForUser.getToken();
  // $scope.id = $stateParams.eventID;
  $scope.currentEventID = ServiceForEvents.getEvent();
  console.log("event id: " + $scope.currentEventID);
  var actualCost = 0;
  var estimatedCost = 0;

    ServiceForItems.getListItems($scope.currentEventID, $scope.token).success(function (data)
  {
    console.log("retrieved list " + data);
    $scope.itemList = data;

    //calculate total
    for(var i=0; i<data.length; i++){
      var items = data[i];
      console.log("this item actual cost: " + items.actCost + " this item estimated cost: " + items.estCost);
      var actCost = items.actCost;
      var estCost = items.estCost;
      actualCost = actualCost + actCost;
      estimatedCost = estimatedCost + estCost;
      console.log("total actual: " + actualCost + " total estimated: " + estimatedCost);
      $scope.actualTotal = parseFloat(actualCost);
      $scope.estimatedTotal = parseFloat(estimatedCost);
    }

    $scope.divideTotal = function (num) {
      $scope.actualSplit = parseFloat(actualCost/num).toFixed(2);
      $scope.estimatedSplit = parseFloat(estimatedCost/num).toFixed(2);
    }


  }
  );

  $scope.checkClaimed = function (toClaim) {

    if (toClaim.hasOwnProperty('whoseBringing')) {
      // $scope.claimed = true;
      return true;
      console.log ("claimed");
    }
  }

  $scope.claimItem = function (toClaim) {
    $scope.token = ServiceForUser.getToken();

    console.log("claiming item " + toClaim.ListID + " from event " + $scope.currentEventID);
    ServiceForItems.claimItem($scope.currentEventID, toClaim.ListID, $scope.token).success(function (data) {
      console.log("successfully claimed " + toClaim.ListID + " claimed by " + toClaim.whoseBringing);
      $window.location.reload();
    });
  };

  $scope.addItem = function () {
    $scope.token = ServiceForUser.getToken();

    console.log("adding item " + $scope.newItem.item + " to event " + $scope.currentEventID);
    ServiceForItems.createListItem($scope.currentEventID, $scope.newItem, $scope.token).success(function (data) {
      console.log("successfully added " + $scope.newItem.item);
      $window.location.reload();
    });
  };

  $scope.updateItem = function (toUpdate) {
    $scope.token = ServiceForUser.getToken();

    console.log("updating item " + toUpdate.ListID + " from event " + $scope.currentEventID);
    ServiceForItems.updateItem($scope.currentEventID, toUpdate.ListID, toUpdate, $scope.token).success(function (data) {
      console.log("successfully added " + toUpdate.ListID);
      $window.location.reload();
    });
  };

  $scope.deleteItem = function (toDelete) {
    $scope.token = ServiceForUser.getToken();

    console.log("deleting item " + toDelete.ListID + " from event " + $scope.currentEventID);
    ServiceForItems.deleteItem($scope.currentEventID, toDelete.ListID, $scope.token).success(function (data) {
      console.log("successfully deleted " + toDelete.ListID);
      $window.location.reload();
    });
  };

})
.controller('EventBudgetController', function ($scope, ServiceForEvents, ServiceForUser, $window, $location){
  $scope.token = ServiceForUser.getToken();
  $scope.currentEvent = ServiceForEvents.getEvent();
  $scope.isPayingCount = 0;
  $scope.total = 0;
  $scope.myStatus = ServiceForEvents.getEventStatus();
  console.log($scope.myStatus);

  //to get actual total
  // ServiceForEvents.getEventById($scope.currentEvent, $scope.token).success(function (data) {
  //     $scope.event = data;
  // });
  $scope.getBudget = function(){ServiceForEvents.getBudget($scope.currentEvent, $scope.token).success(function (data)
  {
    console.log("budget section of " + $scope.currentEvent);
    $scope.results = data;
    for(var i=0; i<data.length; i++){
      var items = data[i];
      if (items.isPaying) $scope.isPayingCount++;
      $scope.total += items.claimedValue;
    }
  });}

  $scope.getBudget();

  $scope.checkAttending = function (toCheck) {
    if (toCheck == "Attending" || toCheck == "Owner") {
      return true;
    }
  }

  $scope.updateIsPaying = function (idToUpdate, statusToUpdate) {
    $scope.data = {};
    $scope.data.answer = statusToUpdate;
    console.log(statusToUpdate);
    console.log("changing " + statusToUpdate + " from user " + idToUpdate);

    ServiceForEvents.updateIsPaying($scope.currentEvent, idToUpdate, $scope.data, $scope.token).success(function (data) {
      console.log("successfully changed " + statusToUpdate + " of " + idToUpdate);
      $scope.getBudget();
    });
  };

  $scope.checkIsOwner = function(){
    if($scope.myStatus == "Owner"){
      return false;
    }
    else {
      return true;
    }
  }
})
.controller('AccountController', function ($scope, $location)
{

})
.controller('EventChatController', function($scope, $window, $location, ServiceForMessages, ServiceForUser, ServiceForEvents){
  $scope.token = ServiceForUser.getToken();
  $scope.currentEvent = ServiceForEvents.getEvent();
  $scope.chat = {};

  ServiceForMessages.getMessages($scope.currentEvent, 1, $scope.token).success(function(data)
  {
    if(data[0])
    {
    $scope.messageList = data[0].messages;
    console.log(data);
    console.log(data[0].messages)
    }
  })

  $scope.sendMessage = function(chat){
    ServiceForMessages.sendMessage($scope.chat, $scope.currentEvent, $scope.token).success(function(data)
  {
    console.log("Message sent.");
    $scope.chat.message = "";
    $window.location.reload();
  })
  }
})

// angular.module('userController', [])
.controller('RegisterUserController', function ($scope, $location, ServiceForUser) {

    //Create a scope to determine if form as been submitted
    $scope.submitted = false;
    //Method that is called when the form is submitted
    $scope.signupForm = function () {
        //If the form is fields are valid
        if ($scope.register_form.$valid) {
            console.log($scope.register_form);
            ServiceForUser.registerUser($scope.user).success(function (data) {
                if(data.error != null){
                    $location.path('/Error');
                }
                else
                {
                    $location.path('/events');
                }
                console.log("Saved User.");
                //Reset values on the page once new user is registered.
                $scope.user.email = "";
                $scope.user.friendlyName = "";
                $scope.user.hashPassword = "";
                $scope.register_form.$setPristine();
                $scope.register_form.submitted = false;
                //$location.path('/Login');
            }
            );

          //If the form is not valid, tell that the form has been submitted to display validations
        } else {
            $scope.register_form.submitted = true;
            console.log($scope.register_form);
            console.log($scope.submitted);
        }
    }

});
