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

            member.txtBudgetMember = (TextView) rowView.findViewById(R.id.budget_list_friend_id);
            member.imgBudgetProfilePic = (ImageView) rowView.findViewById(R.id.budget_list_profile_image);
            member.isPaying = (CheckBox) rowView.findViewById(R.id.budget_list_isPaying);

            rowView.setTag(member);
        }
        else
        {
            member = (BudgetHolder)rowView.getTag();
        }
        member.txtBudgetMember.setTag(position);
        member.txtBudgetMember.setText(budgetList.get(position).memberName);
        member.imgBudgetProfilePic.setImageResource(R.drawable.ic_account_circle_blue_24dp);
        if(budgetList.get(i).isPaying == true){
            member.isPaying.setChecked(true);
        } else {
            member.isPaying.setChecked(false);
        }
        if(budgetList.get(i).status == MemberStatus.OWNER){
            member.isPaying.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    try {
                        if (member.isPaying.isChecked()) {
                            ((BudgetListActivity) context).setPaying(budgetList.get(i).memberId, true);
                        }
                        else{
                            ((BudgetListActivity) context).setPaying(budgetList.get(i).memberId, false);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        else if(budgetList.get(i).status == MemberStatus.ATTENDING){
            member.isPaying.setClickable(false);
        }





        return rowView;
    }


    static class BudgetHolder
    {
        TextView txtBudgetMember;
        ImageView imgBudgetProfilePic;
        CheckBox isPaying;
    }

}
