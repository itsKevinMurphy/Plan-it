//Load datbase connection file
var database = require('../database');
//Load the user model Schema
var userSchema = require('../models/user');


var user = module.exports;

//Create a new user
user.createUser = function(req, res, next ){

  //Create new user object
  var userObj = new database.userModel({
    "friendlyName": req.body.friendlyName,
    "email": req.body.email,
    "hashPassword": req.body.hashPassword,
  });

  //Find an existing email address, due to unique constraint on field
  database.userModel.findOne({"email": userObj.email}, function(err, data){
    //If no errors found
    if(!err)
    {
      //If email already exists
      if(data)
      {
      console.log("Email already registered.")
      res.status(409).send('Duplicate email address found.')
      }
      else
      {
        //Save the new user to database
        userObj.save(function(err) {
          if (err)
            console.log(err);
          else
            res.sendStatus(201);
        });
      }
    }
    else {
      res.sendStatus(500);
    }
  });

};


user.findUserByID = function(req, res, next)
{

};

user.updateUser = function(req, res, next)
{

};

//Add a friend to an existing user
user.addNewFriend = function(req, res, next)
{

}
