//import all npm packages needed
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');
var increment = require('mongoose-auto-increment');

var database = require('./database');
//import config file
var config = require('./config');

//import all controllers
var eventCtrl = require('./controllers/event');
var userCtrl = require('./controllers/user');

var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: false
}));
app.disable('etag');


database.createConnection();

app.post("/events", eventCtrl.createEvent);
app.get("/events/:id", eventCtrl.getEventById);
app.get("/events", eventCtrl.getAllEvents);
app.post("/user", userCtrl.createUser);
app.post('/events/:id/list', eventCtrl.createListItem);
app.get('/events/:id/list', eventCtrl.getListItems);
app.post("/user/:id/friend", userCtrl.addNewFriend);


app.listen(80, function() {
  console.log("Server is running on port 80");
});
