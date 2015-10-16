var mongoose = require('mongoose');
var database = require('../database');

var event = new mongoose.Schema({
  EventID: Number,
  Members: {
    UserId: mongoose.Schema.ObjectId,
    Nofications: [{
        itemList: Boolean,
        messageBoard: Boolean,
        isAttending: { type: String, enum: ['invited', 'Attending', 'Declined', 'Left', 'Owner'] }
    }]
  },
  itemList: {
    List: [{
      item: String,
      whoseBringing: String,
      estCost: Number,
      actCost: Number
    }],
    totalEstCost: Number,
    totalActCost: Number
  },
  what: String,
  why: String,
  where: String,
  when: String,
  picture: String
});

module.exports = event;
