package com.plan_it.mobile.plan_it;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Kristian on 13/12/2015.
 */
public class BudgetListAdapter extends ArrayAdapter<Budget> {
    ArrayList<Budget> budgetList;
    Context context;
    int resource;
    BudgetHolder budget;
    public BudgetListAdapter(Context context, int resource, ArrayList<Budget> budgetList){
        super(context, resource, budgetList);
        this.context = context;
        this.resource = resource;
        this.budgetList = budgetList;
    }

    public View getView (final int i, View view, ViewGroup parent) {
        View rowView = view;
       // final int i = position;
        if(rowView == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(resource, parent, false);

            budget = new BudgetHolder();

            budget.txtBudgetMember = (TextView) rowView.findViewById(R.id.budget_list_friend_id);
            budget.txtBudgetContribution = (TextView) rowView.findViewById(R.id.budget_list_contribution);
            budget.txtBudgetAmountToPay = (TextView) rowView.findViewById(R.id.budget_list_amount_to_pay);
            budget.isPaying = (CheckBox) rowView.findViewById(R.id.budget_list_is_paying);
        }
        else
        {
            budget = (BudgetHolder)rowView.getTag();
        }
        budget.txtBudgetMember.setTag(i);
        budget.txtBudgetMember.setText(budgetList.get(i).userName);
        budget.txtBudgetContribution.setText(String.valueOf(budgetList.get(i).sumActCost));
        budget.txtBudgetAmountToPay.setText(String.format("%.2f", budgetList.get(i).toPay));
        if(ViewEventActvity.isOwner == IsAttending.OWNER){
            budget.isPaying.setEnabled(true);
        }
        else{
            budget.isPaying.setEnabled(false);
        }
        budget.isPaying.setChecked(budgetList.get(i).isPaying);
        budget.isPaying.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        ((BudgetListActivity) context).setPaying(budgetList.get(i).userId, budgetList.get(i).isPaying);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        rowView.setTag(budget);
        return rowView;
    }


    static class BudgetHolder
    {
        TextView txtBudgetMember;
        TextView txtBudgetContribution;
        TextView txtBudgetAmountToPay;
        CheckBox isPaying;
    }


}
