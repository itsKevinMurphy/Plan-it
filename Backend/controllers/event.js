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

event.getEventById = function(req, res, next){
  database.eventModel.findOne({"EventID" : req.params.id}, function(err, event){
    if(err)
      console.log(err);
    else
      res.json(event);
  });
}

event.getAllEvents = function(req, res, next){
  database.eventModel.find({}, function(err, events){
    if(err)
      console.log(err);
    else {
      res.json(events);
    }
  });
}

event.updateEvent = function(req, res, next){

}

event.deleteEvent = function(req, res, next){

}
