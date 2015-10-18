package com.plan_it.mobile.plan_it;

/**
 * Created by Kevin on 17-Oct-2015.
 */
enum IsAttending{INVITED, ATTENDING, DECLINED, LEFT, OWNER}
public class Event {
    String name;
    String owner;
    String description;
    int photoId;
    String date;
    IsAttending isAttending;
    boolean itemList;
    boolean messageBoard;

    Event(String name, String owner, String description, int photoId, String date, IsAttending isAttending, boolean itemList, boolean messageBoard) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.photoId = photoId;
        this.date = date;
        this.isAttending = isAttending;
        this.itemList = itemList;
        this.messageBoard = messageBoard;
    }
}
