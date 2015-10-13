var database = require('../database');
var eventModel = require('../models/event');

var event = module.exports;

event.createEvent = function(req, res, next){
  var event = new database.eventModel({
    "what" : req.body.what,
    "why" : req.body.why,
    "where" : req.body.where,
    "when" : req.body.when
  });

  event.save(function(err){
    if(err)
      console.log(err);
    else
      res.sendStatus(201);
  });
}

event.viewEvent = function(req, res, next){

}

event.updateEvent = function(req, res, next){

}

event.deleteEvent = function(req, res, next){

}
