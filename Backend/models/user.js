//Load mongoose model and Schema
var mongoose = require('mongoose');
var Schema = mongoose.Schema;
//Load Database Configuration file
var database = require('../database');

//Create the User Schema
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

//Export the User Schema to be used by the controller, etc...
module.exports = userSchema;
