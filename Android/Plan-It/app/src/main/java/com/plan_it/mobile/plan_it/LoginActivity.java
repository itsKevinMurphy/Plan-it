package com.plan_it.mobile.plan_it;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Mshohid on 2015-11-17.
 *
 */


public class LoginActivity extends Activity {

        public String token="";
        EditText etUser, etPass;
        ProgressDialog prgDialog;
        // Error Msg TextView Object
        TextView errorMsg;


    public void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUser=(EditText)findViewById(R.id.editText);
        etPass=(EditText)findViewById(R.id.editTextPassword);


        // Instantiate Progress Dialog object
        prgDialog = new ProgressDialog(this);
        // Set Progress Dialog Text
        prgDialog.setMessage("Please wait...");
        // Set Cancelable as False
        prgDialog.setCancelable(false);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
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

    public void loginUser(View v) {
        String user = etUser.getText().toString();
        String password = etPass.getText().toString();
        RequestParams params = new RequestParams();
        // When Email Edit View and Password Edit View have values other than Null
        if ((password != null) && (user != null)) {
            params.put("username", user);
            // Put Http parameter password with value of Password Edit Value control
            params.put("password", password);

            getLogin(params);
        }

    }

    public void getLogin(RequestParams params){

        RestClient.get("login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    try {
                        JSONObject res = new JSONObject();
                        token = res.getString("x-access-token");

                        Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                        Intent homeIntent = new Intent(getApplicationContext(), ViewEventActvity.class);
                        startActivity(homeIntent);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Username and password not found", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void register(View v)
    {
        Intent register=new Intent(this, RegisterActivity.class );
        startActivity(register);
    }
}

