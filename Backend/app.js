var express = require('express');
var event = require('./controllers/event');
var app = express();

app.post("/events", event.postEvent);
app.get("/events/:id", event.createEvent)

app.listen(80, function(){
  console.log("Server is running on port 80");
});
