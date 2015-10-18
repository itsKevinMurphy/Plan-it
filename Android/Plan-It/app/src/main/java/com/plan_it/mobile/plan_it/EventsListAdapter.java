package com.plan_it.mobile.plan_it;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 13-Oct-2015.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventsViewHolder> {
    public static String filter = "NONE";
    private List<Event>mEvents;
    public static class EventsViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView owner;
        TextView description;
        ImageView photo;
        TextView date;
        ImageView button1;
        ImageView button2;
        ImageView addEvent;


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
            addEvent = (ImageView)itemView.findViewById(R.id.event_list_add_button);

        }
        public void bind(Event event) {
            name.setText(event.name);
        }
    }
    List<Event> events;

    EventsListAdapter(Context context, List<Event> events){
        this.events = events;
    }
    @Override
    public int getItemCount() {
        return events.size();
    }
    public void setEvents(List<Event> events) {
        mEvents = new ArrayList<>(events);
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View eventView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_card, viewGroup, false);
        EventsViewHolder pvh = new EventsViewHolder(eventView);
             return pvh;
    }
    @Override
    public void onBindViewHolder(EventsViewHolder eventsViewHolder, int i) {

        if(events.get(i).isAttending == IsAttending.INVITED){
            eventsViewHolder.button1.setImageResource(R.drawable.ic_thumb_up_green_24dp);
            eventsViewHolder.button2.setImageResource(R.drawable.ic_thumb_down_red_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 249, 255, 145));
        }
        else if(events.get(i).isAttending == IsAttending.ATTENDING)
        {
            eventsViewHolder.button2.setImageResource(R.drawable.ic_delete_grey_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 155, 255, 118));
        }
        else if(events.get(i).isAttending == IsAttending.LEFT)
        {
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 255, 165, 171));
        }
        else if(events.get(i).isAttending == IsAttending.DECLINED)
        {
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 255, 165, 171));
        }
        else
        {
            eventsViewHolder.button2.setImageResource(R.drawable.ic_edit_blue_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 255, 254, 199));
        }
        if(filter == "NONE") {
            eventsViewHolder.name.setText(events.get(i).name);
            eventsViewHolder.description.setText(events.get(i).description);
            eventsViewHolder.date.setText(events.get(i).date);
            eventsViewHolder.owner.setText(events.get(i).owner);
            eventsViewHolder.photo.setImageResource(events.get(i).photoId);
        }
        if(filter == "INVITED" && events.get(i).isAttending == IsAttending.INVITED)
        {
            eventsViewHolder.name.setText(events.get(i).name);
            eventsViewHolder.description.setText(events.get(i).description);
            eventsViewHolder.date.setText(events.get(i).date);
            eventsViewHolder.owner.setText(events.get(i).owner);
            eventsViewHolder.photo.setImageResource(events.get(i).photoId);
        }
        if(filter == "ATTENDING" && events.get(i).isAttending == IsAttending.ATTENDING)
        {
            eventsViewHolder.name.setText(events.get(i).name);
            eventsViewHolder.description.setText(events.get(i).description);
            eventsViewHolder.date.setText(events.get(i).date);
            eventsViewHolder.owner.setText(events.get(i).owner);
            eventsViewHolder.photo.setImageResource(events.get(i).photoId);
        }
        if(filter == "DECLINED" && events.get(i).isAttending == IsAttending.DECLINED)
        {
            eventsViewHolder.name.setText(events.get(i).name);
            eventsViewHolder.description.setText(events.get(i).description);
            eventsViewHolder.date.setText(events.get(i).date);
            eventsViewHolder.owner.setText(events.get(i).owner);
            eventsViewHolder.photo.setImageResource(events.get(i).photoId);
        }
        if(filter == "OWNER" && events.get(i).isAttending == IsAttending.OWNER)
        {
            eventsViewHolder.name.setText(events.get(i).name);
            eventsViewHolder.description.setText(events.get(i).description);
            eventsViewHolder.date.setText(events.get(i).date);
            eventsViewHolder.owner.setText(events.get(i).owner);
            eventsViewHolder.photo.setImageResource(events.get(i).photoId);
        }

    }
    public void animateTo(List<Event> events) {
        applyAndAnimateRemovals(events);
        applyAndAnimateAdditions(events);
        applyAndAnimateMovedItems(events);
    }

    private void applyAndAnimateRemovals(List<Event> newEvents) {
        for (int i = events.size() - 1; i >= 0; i--) {
            final Event event = events.get(i);
            if (!newEvents.contains(event)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Event> newEvents) {
        for (int i = 0, count = newEvents.size(); i < count; i++) {
            final Event event = newEvents.get(i);
            if (!events.contains(event)) {
                addItem(i, event);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Event> newEvents) {
        for (int toPosition = newEvents.size() - 1; toPosition >= 0; toPosition--) {
            final Event event = newEvents.get(toPosition);
            final int fromPosition = events.indexOf(event);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Event removeItem(int position) {
        final Event event = events.remove(position);
        notifyItemRemoved(position);
        return event;
    }

    public void addItem(int position, Event event) {
        events.add(position, event);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Event event = events.remove(fromPosition);
        events.add(toPosition, event);
        notifyItemMoved(fromPosition, toPosition);
    }
}

