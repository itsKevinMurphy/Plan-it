//import all npm packages needed
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');
var increment = require('mongoose-auto-increment');


//import config file
var config = require('./config');

//import all controllers
var eventCtrl = require('./controllers/event');

var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.disable('etag');

var database = require('./database');
database.createConnection();

app.post("/events", eventCtrl.createEvent);
app.get("/events/:id", eventCtrl.createEvent);

app.listen(80, function(){
  console.log("Server is running on port 80");
});
