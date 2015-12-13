package com.plan_it.mobile.plan_it;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kristian on 13/12/2015.
 */
public class BudgetListAdapter extends ArrayAdapter<Members> {
    ArrayList<Members> budgetList;
    Context context;
    int resource;
    BudgetHolder member;

    public BudgetListAdapter(Context context, int resource, ArrayList<Members> budgetList){
        super(context, resource, budgetList);
        this.context = context;
        this.resource = resource;
        this.budgetList = budgetList;
    }

    public View getView(int position, View view, ViewGroup parent) {
        View rowView = view;
        final int i = position;
        if(rowView == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(resource, parent, false);

            member = new BudgetHolder();

            member.txtAttendeeName = (TextView) rowView.findViewById(R.id.budget_list_friend_id);
            member.imgProfilePic = (ImageView) rowView.findViewById(R.id.budget_list_profile_image);
            member.isPaying = (CheckBox) rowView.findViewById(R.id.budget_list_isPaying);

            rowView.setTag(member);
        }
        else
        {
            member = (BudgetHolder)rowView.getTag();
        }
        member.txtAttendeeName.setTag(position);
        member.txtAttendeeName.setText(budgetList.get(position).memberName);
        member.imgProfilePic.setImageResource(R.drawable.ic_account_circle_blue_24dp);

        return rowView;
    }


    static class BudgetHolder
    {
        TextView txtAttendeeName;
        ImageView imgProfilePic;
        CheckBox isPaying;
    }

}
