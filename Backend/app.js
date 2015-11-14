//import all npm packages needed
var express = require('express');
var mongoose = require('mongoose');
var increment = require('mongoose-auto-increment');

module.exports = app = express();

var database = require('./database');
//import config file
var config = require('./config');

//import all controllers
var eventCtrl = require('./controllers/event');
var userCtrl = require('./controllers/user');

database.createConnection();

app.get('/events', eventCtrl.getAllEvents);
app.post('/events', eventCtrl.createEvent);
app.get('/events/:id([0-9]*$)', eventCtrl.getEventById);
app.put('/events/:id([0-9]*$)', eventCtrl.updateEvent);
app.get('/events/user', eventCtrl.getUsersEvents);
app.delete('/events/:id([0-9]*$)', eventCtrl.deleteEvent);
app.post('/events/:id([0-9]*$)/list', eventCtrl.createListItem);
app.get('/events/:id([0-9]*$)/list', eventCtrl.getListItems);
app.post('/events/:id([0-9]*$)/list/:item([0-9]*$)', eventCtrl.claimItem);
app.delete('/events/:id([0-9]*$)/list/:item([0-9]*$)', eventCtrl.deleteItem);
app.put('/events/:id([0-9]*$)/list/:item([0-9]*$)', eventCtrl.updateItem);
app.post("/events/:id([0-9]*$)/list", eventCtrl.createListItem);
app.get("/events/:id([0-9]*$)/list", eventCtrl.getListItems);
app.post("/user/:id([0-9]*$)/friend", userCtrl.addNewFriend);
app.get("/user/:id([0-9]*$)/friend", userCtrl.getAllFriends);
app.get("/search/:id([0-9]*$)/user", userCtrl.findUserByID);
app.get("/search/:param/friend", userCtrl.findUserByFriendlyNameOrEmail);
app.delete("/user/:id([0-9]*$)/friend/:friendId([0-9]*$)", userCtrl.removeFriend);
app.post("/user", userCtrl.createUser);
app.post("/login", userCtrl.loginUser);

app.listen(3000, function() {
  console.log("Server is running on port 3000");
});
