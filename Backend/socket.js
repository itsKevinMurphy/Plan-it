var io = require('socket.io')();
var socketioJwt   = require("socketio-jwt");

//var config = require('./config');

// io.use(socketioJwt.authorize({
//   secret: 'captainplanet',
//   handshake: true
// }));

io.on('connection', function(socket){
  socket.on('event', function(data){
    socket.join(data.id);
    io.to(data.id).emit('event created', '');
    socket.leave(data.id);
  });
});

console.log("Server is running on port 3001");
io.listen(3001);
