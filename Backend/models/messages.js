//Load mongoose model and Schema
var mongoose = require('mongoose');
var Schema = mongoose.Schema;
//Load Database Configuration file
var database = require('../database');

var messagesSchema = new Schema(
  {
    events: [{ message: String, eventID: Number}]
  }
);

module.exports = messagesSchema;
