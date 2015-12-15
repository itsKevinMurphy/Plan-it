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

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Kristian on 13/12/2015.
 */
public class BudgetListAdapter extends ArrayAdapter<Budget> {
    ArrayList<Budget> budgetList;
    Context context;
    int resource;
    BudgetHolder member;
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

            member = new BudgetHolder();

            member.txtBudgetMember = (TextView) rowView.findViewById(R.id.budget_list_friend_id);
            member.txtBudgetContribution = (TextView) rowView.findViewById(R.id.budget_list_contribution);
            member.txtBudgetAmountToPay = (TextView) rowView.findViewById(R.id.budget_list_amount_to_pay);
            member.isPaying = (CheckBox) rowView.findViewById(R.id.budget_list_is_paying);
        }
        else
        {
            member = (BudgetHolder)rowView.getTag();
        }
        member.txtBudgetMember.setTag(i);
        member.txtBudgetMember.setText(budgetList.get(i).userName);
        member.txtBudgetContribution.setText(String.valueOf(budgetList.get(i).sumActCost));
        member.txtBudgetAmountToPay.setText(String.valueOf(budgetList.get(i).toPay));
        if(budgetList.get(i).isPaying == true){
            member.isPaying.setChecked(true);
        }
        else{
            member.isPaying.setChecked(false);
        }
        /*member.isPaying.setChecked(budgetList.get(i).isPaying);
            member.isPaying.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        ((BudgetListActivity) context).setPaying(budgetList.get(i).memberId, budgetList.get(i).isPaying);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });*/
        rowView.setTag(member);
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
