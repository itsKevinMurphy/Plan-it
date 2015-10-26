package com.plan_it.mobile.plan_it;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EventsListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private List<Event> events;
    private List<Event> mEvents;
    private EventsListAdapter adapter;
    private RecyclerView events_recycler_view;
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
        adapter = new EventsListAdapter(this, events);
        setContentView(R.layout.activity_events_list);
        events_recycler_view = (RecyclerView)findViewById(R.id.events_list_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        events_recycler_view.setLayoutManager(llm);
        events_recycler_view.setAdapter(adapter);

        mEvents = new ArrayList<>();

        for (Event event: events) {
            mEvents.add(new Event(event.name, event.owner, event.description, event.photoId,event.date,event.isAttending,event.itemList, event.messageBoard));
            Log.d("mEvents", "name " + event.name + "owner " + event.owner + "description " + event.description + "photoId " + event.photoId);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events_list, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onQueryTextChange(String query) {

        List<Event> filteredModelList;
        if (query == "INVITED" || query == "ATTENDING" || query == "DECLINED" || query == "OWNER")
        {
            filteredModelList = new ArrayList<>();
            for (Event event : mEvents) {
                final String text = event.isAttending.toString();
                if (text.contains(query)) {
                    filteredModelList.add(event);
                }
            }
            adapter.animateTo(filteredModelList);
            events_recycler_view.scrollToPosition(0);

        } else if (query == "NONE") {
            filteredModelList = new ArrayList<>();
            for (Event event : mEvents)
            {

                filteredModelList.add(event);
            }
            adapter.animateTo(mEvents);
            events_recycler_view.scrollToPosition(0);
        }
        else{
            filteredModelList = filter(mEvents, query);
            adapter.animateTo(filteredModelList);
            events_recycler_view.scrollToPosition(0);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    private List<Event> filter(List<Event> events, String query) {

        query = query.toLowerCase();

            final List<Event> filteredModelList = new ArrayList<>();
            for (Event event : events) {
                final String text = event.name.toLowerCase();
                if (text.contains(query)) {
                    filteredModelList.add(event);
                    Log.d("event filtered", "name " + event.name + "owner " + event.owner + "description " + event.description + "photoId " + event.photoId);
                }
            }
           return filteredModelList;
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
        if (id == R.id.action_filter)
        {
            FilterEvents();
        }
        if (id == R.id.action_friendsList)
        {
            Intent intent = new Intent(this, FriendsListActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_create_new_event)
        {
            Intent intent = new Intent(this, CreateEventActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void FilterEvents()
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.menu_filter_list);
        dialog.show();
        Button invited = (Button)dialog.findViewById(R.id.filterButtonInvited);
        Button attending = (Button)dialog.findViewById(R.id.filterButtonAttending);
        Button declined = (Button)dialog.findViewById(R.id.filterButtonDeclined);
        Button owner = (Button)dialog.findViewById(R.id.filterButtonOwner);
        Button showAll = (Button)dialog.findViewById(R.id.filterButtonAll);

        invited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsListAdapter.filter = "INVITED";
                Log.d("Filter: ", EventsListAdapter.filter);
                onQueryTextChange("INVITED");
                dialog.dismiss();
            }
        });
        attending.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EventsListAdapter.filter = "ATTENDING";
            Log.d("Filter: ", EventsListAdapter.filter);
            onQueryTextChange("ATTENDING");
            dialog.dismiss();
        }
    });
        declined.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EventsListAdapter.filter = "DECLINED";
            Log.d("Filter: ", EventsListAdapter.filter);
            onQueryTextChange("DECLINED");
            dialog.dismiss();
        }
    });
        owner.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EventsListAdapter.filter = "OWNER";
            onQueryTextChange("OWNER");
            Log.d("Filter: ", EventsListAdapter.filter);
            dialog.dismiss();
        }
    });
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsListAdapter.filter = "NONE";
                onQueryTextChange("NONE");
                Log.d("Filter: ", EventsListAdapter.filter);
                dialog.dismiss();
            }
        });
    }

}
