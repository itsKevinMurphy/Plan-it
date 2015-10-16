var database = module.exports;

var mongoose = require('mongoose');
var increment = require('mongoose-auto-increment');
var config = require('./config');
var eventjs = require('./models/event');
var userjs = require('./models/user')

database.createConnection = function(){
  mongoose.connect(config.db);
  var db = mongoose.connection;
  increment.initialize(db);
  this.initEventIncrement();
  db.on('error', console.error.bind(console, 'connection error:'));
  db.on('open', function () {
    console.log("connection made to database");
  });
}

database.initEventIncrement = function(){
  eventjs.plugin(increment.plugin, {model : "Event", field: 'EventID', startAt: 1});
}

//database.connection = db;

database.eventModel = mongoose.model("Event", eventjs);
//Create User model
database.userModel = mongoose.model("User", userjs);
