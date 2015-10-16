package com.plan_it.mobile.plan_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class EventsListActivity extends AppCompatActivity {

    private List<Event> events;
    enum IsAttending{INVITED, ATTENDING, DECLINED, LEFT, OWNER}

    void initializeData() {
        events = new ArrayList<>();
        events.add(new Event("Riot Fest", "Kevin Murphy", "Hey Guys, Let's go listen to some Music", R.drawable.riot_fest_325, "13/11/2015", IsAttending.OWNER, true, true));
        events.add(new Event("Itza Time to do Camps", "Mohammed Sumon", "I would like to go camping", R.drawable.canot_camp_000, "10/12/2015", IsAttending.INVITED, false, false));
        events.add(new Event("Snowboarding", "Joanne Tanson", "LET'S GO SNOWBOARDING", R.drawable.victoria_snowboard_mount_washington_small, "03/01/2016", IsAttending.ATTENDING, true, false));
        events.add(new Event("Disney World", "Stephanie DeLongo", "Hey Guys, Let's go listen to some Music", R.drawable.mickey_mouse_icon, "30/03/2016", IsAttending.INVITED, true, true));
        events.add(new Event("The Cottage", "Cottage MacGee", "Hey Guys, Let's go listen to some Music", R.drawable.cottage_26_waterside_248, "12/12/2015", IsAttending.DECLINED, false, true));
        events.add(new Event("Let Us Camp", "Coolio McCampington", "Hey Guys, Let's go listen to some Music", R.drawable.canot_camp_000, "12/25/2015", IsAttending.LEFT, true, false));
        events.add(new Event("Riot Fest", "Kevin Murphy", "Hey Guys, Let's go listen to some Music", R.drawable.riot_fest_325, "13/11/2015", IsAttending.OWNER, true, true));
        events.add(new Event("Itza Time to do Camps", "Mohammed Sumon", "I would like to go camping", R.drawable.canot_camp_000, "10/12/2015", IsAttending.INVITED, false, false));
        events.add(new Event("Snowboarding", "Joanne Tanson", "LET'S GO SNOWBOARDING", R.drawable.victoria_snowboard_mount_washington_small, "03/01/2016", IsAttending.ATTENDING, true, false));
        events.add(new Event("Disney World", "Stephanie DeLongo", "Hey Guys, Let's go listen to some Music", R.drawable.mickey_mouse_icon, "30/03/2016", IsAttending.INVITED, true, true));
        events.add(new Event("The Cottage", "Cottage MacGee", "Hey Guys, Let's go listen to some Music", R.drawable.cottage_26_waterside_248, "12/12/2015", IsAttending.DECLINED, false, true));
        events.add(new Event("Let Us Camp", "Coolio McCampington", "Hey Guys, Let's go listen to some Music", R.drawable.canot_camp_000, "12/25/2015", IsAttending.LEFT, true, false));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData();
        EventsListAdapter adapter = new EventsListAdapter(events);
        setContentView(R.layout.activity_events_list);
        RecyclerView events_recycler_view = (RecyclerView)findViewById(R.id.events_list_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        events_recycler_view.setLayoutManager(llm);
        events_recycler_view.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events_list, menu);
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



    class Event {
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

}
