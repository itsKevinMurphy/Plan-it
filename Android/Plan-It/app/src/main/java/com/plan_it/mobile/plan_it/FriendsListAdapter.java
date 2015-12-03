package com.plan_it.mobile.plan_it;

/**
 * Created by Kevin on 30-Nov-2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsListAdapter extends ArrayAdapter<String> {
    private String[] friendID;
    private Integer[] profilePicID;
    private  boolean isFavourite;
    private ArrayList friendsList;
    int resource;
    Context context;
    Activity friendsListActivity;

    public FriendsListAdapter(Context context, int resource, String[] friendID, Integer[] profilePicID, boolean isFavourite) {
        super(context, resource);
        this.context = context;
        this.friendID = friendID;
        this.profilePicID = profilePicID;
        this.isFavourite = isFavourite;
    }

    public FriendsListAdapter(Context context, int resource, ArrayList friendsList){
        super(context, resource);
        this.friendsList = friendsList;
        this.resource = resource;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = friendsListActivity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.friends_list_item, null, true);

        TextView txtFriendID = (TextView) rowView.findViewById(R.id.friends_list_friend_id);
        ImageView imgProfilePic = (ImageView) rowView.findViewById(R.id.friends_list_profile_image);
        ImageView imgIsFavourite = (ImageView) rowView.findViewById(R.id.friends_list_favourite_star);

        txtFriendID.setText(friendID[position]);
        imgProfilePic.setImageResource(profilePicID[position]);
        if(isFavourite) {
            imgIsFavourite.setImageResource(R.drawable.ic_favorite_blue_48dp);
        }else{imgIsFavourite.setImageResource(R.drawable.ic_favorite_border_blue_48dp);}
        return rowView;
    };
}
