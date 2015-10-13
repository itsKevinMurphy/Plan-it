//import all npm packages needed
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');

//import all controllers
var eventCtrl = require('./controllers/event');

//import all models
var eventModel = require('./models/event');


var app = express();
app.use(bodyParser.urlencoded({ extended: false }));

app.post("/events", eventCtrl.postEvent);
app.get("/events/:id", eventCtrl.createEvent);

app.listen(80, function(){
  console.log("Server is running on port 80");
});
