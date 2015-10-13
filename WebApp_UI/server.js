var express = require('express'),
    app = express(),
    mongoose = require('mongoose'),
    http = require('http').createServer(app),
    io = require('socket.io').listen(http);

http.listen(4020, function(){
  console.log('the magic happens on *:4020');
});

app.use(express.static(__dirname+'/public'));
