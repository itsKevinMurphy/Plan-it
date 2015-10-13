package com.plan_it.mobile.plan_it;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class EventsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);
        RecyclerView events_recycler_view = (RecyclerView)findViewById(R.id.events_recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        events_recycler_view.setLayoutManager(llm);
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

    enum IsAttending{INVITED, ATTENDING, DECLINED, LEFT, OWNER}

    class Event
    {
        String name;
        String owner;
        String description;
        int photoId;
        String date;
        IsAttending isAttending;
        boolean itemList;
        boolean messageBoard;

        Event(String name, String owner, String description, int photoId, String date, IsAttending isAttending, boolean itemList, boolean messageBoard)
        {
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
