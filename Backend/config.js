var config = module.exports;

var app = require('./app');
var bodyParser = require('body-parser');
var jwt = require('jsonwebtoken');
var cors = require('cors');

config.db = 'mongodb://admin:password@ds056288.mongolab.com:56288/teamawesome-test';
config.secret = 'captainplanet'

app.set('superSecret', config.secret); // secret variable
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: false
}));
app.disable('etag');

app.use(cors());

//Enable CORS on server
app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header('Access-Control-Allow-Methods', 'DELETE, PUT', 'OPTIONS');
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, x-access-token");
  next();
});

//Verify User's Token
app.use(function(req, res, next) {
  // check header or url parameters or post parameters for token
  var token = req.body.token || req.query.token || req.headers['x-access-token'];
  // decode token
  if(req.originalUrl === '/login' || req.originalUrl === '/user'){
    return next();
  }else if(token) {
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
