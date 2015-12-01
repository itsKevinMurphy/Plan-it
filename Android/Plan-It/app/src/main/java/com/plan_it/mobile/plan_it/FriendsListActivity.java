package com.plan_it.mobile.plan_it;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FriendsListActivity extends AppCompatActivity {
    public ArrayList<FriendListModel> friendsList = new ArrayList<>();
    ListView list;
    FriendsListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        fillFriendsList();
        int resurces;
        list = (ListView)findViewById(R.id.friends_list_view);
    }

    public void fillFriendsList()
    {
        friendsList.add(new FriendListModel("KevinMurphy", R.drawable.mickey_mouse_icon, true));
        friendsList.add(new FriendListModel("JoanneTanson", R.drawable.cottage_26_waterside_248, false));
        friendsList.add(new FriendListModel("KristianCastaneda", R.drawable.riot_fest_325, false));
        friendsList.add(new FriendListModel("LukeFarnell", R.drawable.mickey_mouse_icon, true));
        friendsList.add(new FriendListModel("KamranSyed", R.drawable.canot_camp_000, true));
        friendsList.add(new FriendListModel("MohammedSumon", R.drawable.ic_perm_identity_black_24dp, true));
        friendsList.add(new FriendListModel("AminaAbbasi", R.drawable.victoria_snowboard_mount_washington_small, true));
        friendsList.add(new FriendListModel("JeffTyber", R.drawable.ic_account_circle_blue_24dp, false));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
