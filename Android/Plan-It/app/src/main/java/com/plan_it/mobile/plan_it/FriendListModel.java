package com.plan_it.mobile.plan_it;

import android.graphics.Bitmap;

/**
 * Created by Kevin on 01-Dec-2015.
 */
public class FriendListModel {
    private String FriendID = "";
    private Integer ProfilePic;
    private Boolean IsFavourite;

    FriendListModel(String FriendID, int ProfilePic, Boolean IsFavourite) {
        this.FriendID = FriendID;
        this.ProfilePic = ProfilePic;
        this.IsFavourite = IsFavourite;
    }

}