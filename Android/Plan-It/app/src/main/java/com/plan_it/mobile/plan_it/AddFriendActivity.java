package com.plan_it.mobile.plan_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class AddFriendActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
    }

    public void SearchForFriend()
    {
        EditText friendName = (EditText)findViewById(R.id.search_for_friend_input);
        final String friendNameString = friendName.getText().toString();

        Toast.makeText(getApplicationContext(),"Searching for: " + friendNameString, Toast.LENGTH_LONG).show();

        RestClient.get("user",null, LoginActivity.token, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray eventsList)
            {

            }

            @Override
            public void onFailure(int statusCode, Header[] header,Throwable throwable, JSONObject response)
            {
                Toast.makeText(getApplicationContext(), "Could Not Find User: " + friendNameString, Toast.LENGTH_LONG).show();
            }

        });
    }

    public  void AddFriend()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
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
