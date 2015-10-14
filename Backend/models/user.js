//Load mongoose model and Schema
var mongoose = require('mongoose');
var Schema = mongoose.Schema;
//Load Database Configuration file
var database = require('../database');

var userSchema = new Schema(
  {
    UserID: Number,
    friendlyName: { type: String, required: true },
    email: { type: String, required: true, unique: true },
    hashPassword: { type: String, required: true},
    friendList: [{ userID: Number, friendlyName: String, isFavorite: {type: Boolean, default: true } }],
    events: [{ eventID:Number }]
  }
);

module.exports = userSchema;
