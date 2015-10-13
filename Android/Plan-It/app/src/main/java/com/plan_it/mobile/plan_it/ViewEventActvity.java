package com.plan_it.mobile.plan_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewEventActvity extends AppCompatActivity {

    ArrayList<String> attendeeNameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_actvity);

        //get Attendee List and display on view event page
        ListView attendeeList = (ListView)findViewById(R.id.lstAttendees);
        attendeeNameList = new ArrayList<String>();
        getAttendeeNames();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,attendeeNameList);
        attendeeList.setAdapter(arrayAdapter);


    }
    void getAttendeeNames(){
        attendeeNameList.add("Kristian");
        attendeeNameList.add("Joanne");
        attendeeNameList.add("Kevin");
        attendeeNameList.add("Amina");
        attendeeNameList.add("Luke");
        attendeeNameList.add("Mo");
        attendeeNameList.add("Kamran");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_event_actvity, menu);
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
