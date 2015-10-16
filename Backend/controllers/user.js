//Load datbase connection file
var database = require('../database');
//Load the user model Schema
var userSchema = require('../models/user');

var user = module.exports;

user.createUser = function(req, res, next ){
  var userName = req.body.friendlyName;

  console.log(userName);
  console.log("Hello");
};

user.findUserByID = function(req, res, next)
{
  
};

user.updateUser = function(req, res, next)
{

};
