package com.plan_it.mobile.plan_it;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 13-Oct-2015.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventsViewHolder> {
    public static String filter = "NONE";
    public static View view;
    private List<Event>mEvents;
    private Context context;
    public class EventsViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView owner;
        TextView description;
        ImageView photo;
        TextView date;
        ImageView button1;
        ImageView button2;
        ImageView addEvent;

       public EventsViewHolder(final View itemView) {
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
            view = itemView;

           view.setOnClickListener(new View.OnClickListener() {
               @Override public void onClick(View v) {
                   Intent intent = new Intent(context, ViewEventActvity.class);
                   context.startActivity(intent);
                   Toast.makeText(context, name.getText() + " " + description.getText(),
                           Toast.LENGTH_LONG).show();
               }
           });
        }
        public void bind(Event event) {
            name.setText(event.name);
            owner.setText(event.owner);
            description.setText(event.description);
            photo.setImageResource(event.photoId);
            date.setText(event.date);
            IsAttending isAttending = event.isAttending;
            boolean itemList = event.itemList;
            boolean messageBoard = event.messageBoard;
        }
    }

    EventsListAdapter(Context context, List<Event> events)
    {
        this.context = context;
        mEvents = new ArrayList<>(events);
    }
    @Override
    public int getItemCount() {
        return mEvents.size();
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
    public void onBindViewHolder(EventsViewHolder eventsViewHolder, int i)
    {
        final int j = i;
        final Event event = mEvents.get(i);
        eventsViewHolder.bind(event);

        eventsViewHolder.button1.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                Toast.makeText(context, mEvents.get(j).name,
                        Toast.LENGTH_LONG).show();
            }
        });
        eventsViewHolder.button2.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                Toast.makeText(context,mEvents.get(j).description,
                        Toast.LENGTH_LONG).show();
            }
        });

        if(mEvents.get(i).isAttending == IsAttending.INVITED){
            eventsViewHolder.button1.setImageResource(R.drawable.ic_thumb_up_green_24dp);
            eventsViewHolder.button2.setImageResource(R.drawable.ic_thumb_down_red_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 250, 255, 85));
        }
        else if(mEvents.get(i).isAttending == IsAttending.ATTENDING)
        {
            eventsViewHolder.button1.setImageResource(R.drawable.ic_thumb_down_red_24dp);
            eventsViewHolder.button2.setImageResource(R.drawable.ic_photo_library_blue_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 155, 255, 118));
        }
        else if(mEvents.get(i).isAttending == IsAttending.LEFT)
        {
            eventsViewHolder.button1.setImageResource(R.drawable.ic_photo_library_blue_24dp);
            eventsViewHolder.button2.setImageResource(R.drawable.ic_delete_grey_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 255, 191, 92));
        }
        else if(mEvents.get(i).isAttending == IsAttending.DECLINED)
        {
            eventsViewHolder.button1.setImageResource(R.drawable.ic_thumb_up_green_24dp);
            eventsViewHolder.button2.setImageResource(R.drawable.ic_delete_grey_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 255, 165, 171));
        }
        else {
            eventsViewHolder.button1.setImageResource(R.drawable.ic_delete_grey_24dp);
            eventsViewHolder.button2.setImageResource(R.drawable.ic_edit_blue_24dp);
            eventsViewHolder.cv.setCardBackgroundColor(Color.argb(125, 255, 254, 199));
        }

    }
    public void animateTo(List<Event> events) {
        applyAndAnimateRemovals(events);
        applyAndAnimateAdditions(events);
        applyAndAnimateMovedItems(events);
    }

    private void applyAndAnimateRemovals(List<Event> newEvents) {
        for (int i = mEvents.size() - 1; i >= 0; i--) {
            final Event event = mEvents.get(i);
            if (!newEvents.contains(event)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Event> newEvents) {
        for (int i = 0, count = newEvents.size(); i < count; i++) {
            final Event mEvent = newEvents.get(i);
            Log.d("Additions","Position: " + i + " event: " + mEvent.name);
            if (!mEvents.contains(mEvent)) {
                addItem(i, mEvent);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Event> newEvents) {
        for (int toPosition = newEvents.size() - 1; toPosition >= 0; toPosition--) {
            final Event mEvent = newEvents.get(toPosition);
            final int fromPosition = mEvents.indexOf(mEvent);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Event removeItem(int position) {
        final Event event = mEvents.remove(position);
        notifyItemRemoved(position);
        return event;
    }

    public void addItem(int position, Event event) {
        Log.d("addItem","Position: " + position + " event: " + event);
        mEvents.add(position, event);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Event event = mEvents.remove(fromPosition);
        mEvents.add(toPosition, event);
        notifyItemMoved(fromPosition, toPosition);
    }
}

