package com.plan_it.mobile.plan_it;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class BudgetListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    public ArrayList<Budget> bList;
    private SwipeRefreshLayout swipeRefreshLayout;
    ListView budgetList;
    Context context = this;
    int eventID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_list);

        Intent intent = getIntent();
        Bundle eventBundle = intent.getExtras();
        eventID = eventBundle.getInt("eventID");


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_budget_list);
        swipeRefreshLayout.setOnRefreshListener(this);
        try {
            populateBudget();
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        try {
            populateBudget();
            swipeRefreshLayout.setRefreshing(false);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void populateBudget()throws JSONException{
        RestClient.get("events/" + eventID + "/budget", null, LoginActivity.token, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray budgetArray) {
                Log.d("onSuccess: ", budgetArray.toString());
                JSONObject budget = null;
                try {
                    bList = new ArrayList<>();
                    for (int i = 0; i < budgetArray.length(); i++) {
                        budget = budgetArray.getJSONObject(i);
                        String friendlyName = budget.getString("friendlyName");
                        double sumActCost = budget.getDouble("claimedValue");
                        double toPay = budget.getDouble("toPay");
                        double dividedTotal;
                        if(budget.has("dividedTotal")){
                            dividedTotal = budget.getDouble("dividtedTotal");
                        }
                        else{
                            dividedTotal = 0.0;
                        }
                        boolean isPaying;
                        if (!budget.has("isPaying")) {
                            isPaying = true;
                        }
                        else{
                            isPaying = budget.getBoolean("isPaying");
                        }
                        bList.add(new Budget(friendlyName,sumActCost,dividedTotal, toPay, isPaying));
                        Log.d("Member: ", budget.toString());
                    }
                    budgetList = (ListView) findViewById(R.id.budget_list_view);
                    budgetList.setAdapter(new BudgetListAdapter(context, R.layout.list_budget, bList));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] header, Throwable throwable, JSONObject response) {
                Toast.makeText(getApplicationContext(), "FAILURE", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void setPaying(int memberId, boolean isPaying) throws JSONException{
        RequestParams jdata = new RequestParams();
        if(isPaying == true){
            jdata.put("answer",false);
        }
        else if (isPaying == false){
            jdata.put("answer",true);
        }
        RestClient.post("/events/" + eventID + "/paying/" + memberId, jdata, LoginActivity.token, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_budget_list, menu);
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
