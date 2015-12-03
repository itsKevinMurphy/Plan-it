package com.plan_it.mobile.plan_it;

/**
 * Created by Kevin on 01-Dec-2015.
 */
public class FriendListModel {
    public String FriendID;
    public Integer ProfilePic;
    public Boolean IsFavourite;

    FriendListModel(String FriendID, int ProfilePic, Boolean IsFavourite) {
        this.FriendID = FriendID;
        this.ProfilePic = ProfilePic;
        this.IsFavourite = IsFavourite;
    }

}