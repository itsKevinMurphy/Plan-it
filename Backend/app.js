//import all npm packages needed
var express = require('express');
var mongoose = require('mongoose');
var increment = require('mongoose-auto-increment');
// used to create, sign, and verify tokens
var jwt = require('jsonwebtoken');

module.exports = app = express();

var database = require('./database');
//import config file
var config = require('./config');

//import all controllers
var eventCtrl = require('./controllers/event');
var userCtrl = require('./controllers/user');

database.createConnection();

app.post("/events", eventCtrl.createEvent);
app.get("/events/:id", eventCtrl.getEventById);
app.get("/events", eventCtrl.getAllEvents);
app.post("/user", userCtrl.createUser);
app.post('/events/:id/list', eventCtrl.createListItem);
app.get('/events/:id/list', eventCtrl.getListItems);
app.post('/events/:id/list/:item', eventCtrl.claimItem);
app.delete('/events/:id/list/:item', eventCtrl.deleteItem);
app.put('/events/:id/list/:item', eventCtrl.updateItem);
app.put("/events/:id", eventCtrl.updateEvent);
app.delete("/events/:id", eventCtrl.deleteEvent);
app.post("/events/:id/list", eventCtrl.createListItem);
app.get("/events/:id/list", eventCtrl.getListItems);
app.post("/user/:id/friend", userCtrl.addNewFriend);
app.get("/user/:id/friend", userCtrl.getAllFriends);
app.get("/search/:id/user", userCtrl.findUserByID);
app.get("/search/:param/friend", userCtrl.findUserByFriendlyNameOrEmail);
app.delete("/user/:id/friend/:friendId", userCtrl.removeFriend);
app.post("/login", userCtrl.loginUser);

app.listen(3000, function() {
  console.log("Server is running on port 3000");
});
