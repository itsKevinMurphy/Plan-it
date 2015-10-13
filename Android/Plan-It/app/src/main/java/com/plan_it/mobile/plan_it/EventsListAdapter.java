package com.plan_it.mobile.plan_it;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Kevin on 13-Oct-2015.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventsViewHolder>{

    public static class EventsViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView owner;
        TextView description;
        ImageView photo;
        TextView date;
        ImageView button1;
        ImageView button2;

        EventsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.events_list_cv);
            name = (TextView)itemView.findViewById(R.id.event_list_name);
            description = (TextView)itemView.findViewById(R.id.event_list_description);
            owner = (TextView)itemView.findViewById(R.id.event_list_owner);
            date = (TextView)itemView.findViewById(R.id.event_list_countdown);
            photo = (ImageView)itemView.findViewById(R.id.event_list_photo);
            button1 = (ImageView)itemView.findViewById(R.id.event_list_button_left);
            button2 = (ImageView)itemView.findViewById(R.id.event_list_button_right);
        }
    }
    List<EventsListActivity.Event> events;

    EventsListAdapter(List<EventsListActivity.Event> events){
        this.events = events;
    }
    @Override
    public int getItemCount() {
        return events.size();
    }
    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_card, viewGroup, false);
        EventsViewHolder pvh = new EventsViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(EventsViewHolder eventsViewHolder, int i) {
        eventsViewHolder.name.setText(events.get(i).name);
        eventsViewHolder.description.setText(events.get(i).description);
        eventsViewHolder.date.setText(events.get(i).date);
        eventsViewHolder.owner.setText(events.get(i).owner);
        eventsViewHolder.photo.setImageResource(events.get(i).photoId);

    }
}
