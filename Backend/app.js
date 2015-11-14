//import all npm packages needed
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');
var increment = require('mongoose-auto-increment');

var database = require('./database');
//import config file
var config = require('./config');
// used to create, sign, and verify tokens
var jwt = require('jsonwebtoken');

//import all controllers
var eventCtrl = require('./controllers/event');
var userCtrl = require('./controllers/user');

var app = express();

app.set('superSecret', config.secret); // secret variable
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: false
}));
app.disable('etag');

//Enable CORS on server
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
res.header('Access-Control-Allow-Methods', 'DELETE, PUT');
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

//Verify User's Token
app.use(function(req, res, next) {
  // check header or url parameters or post parameters for token
  var token = req.body.token || req.query.token || req.headers['x-access-token'];

  // decode token
  if(req.originalUrl === '/login' || req.originalUrl === '/user')
  {
    return next();
  }
  else if (token) {
    // verifies secret and checks exp
    jwt.verify(token, app.get('superSecret'), function(err, decoded) {
      if (err) {
        return res.json({ success: false, message: 'Failed to authenticate token.' });
      } else {
        // if everything is good, save to request for use in other routes
        req.decoded = decoded;
        next();
      }
    });
  } else {

    // if there is no token
    // return an error
    return res.status(403).send({
        success: false,
        message: 'No token provided.'
    });

  }
});

database.createConnection();

app.post("/events", eventCtrl.createEvent);
app.get("/events/:id", eventCtrl.getEventById);
app.get("/events", eventCtrl.getAllEvents);
app.post("/user", userCtrl.createUser);
app.post('/events/:id/list', eventCtrl.createListItem);
app.get('/events/:id/list', eventCtrl.getListItems);
app.post('/events/:id/list/:item', eventCtrl.claimItem);
app.delete('/events/:id/list/:item', eventCtrl.deleteItem);
app.put('/events/:id/list/:item', eventCtrl.updateItem);
app.put("/events/:id", eventCtrl.updateEvent);
app.delete("/events/:id", eventCtrl.deleteEvent);
app.post("/events/:id/list", eventCtrl.createListItem);
app.get("/events/:id/list", eventCtrl.getListItems);
app.post("/user/:id/friend", userCtrl.addNewFriend);
app.get("/user/:id/friend", userCtrl.getAllFriends);
app.get("/search/:id/user", userCtrl.findUserByID);
app.get("/search/:param/friend", userCtrl.findUserByFriendlyNameOrEmail);
app.delete("/user/:id/friend/:friendId", userCtrl.removeFriend);
app.post("/login", userCtrl.loginUser);

app.listen(3000, function() {
  console.log("Server is running on port 3000");
});
