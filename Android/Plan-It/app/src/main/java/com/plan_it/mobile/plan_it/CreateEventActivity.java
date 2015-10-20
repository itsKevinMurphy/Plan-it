package com.plan_it.mobile.plan_it;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.*;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateEventActivity extends AppCompatActivity {
    String e_name;
    String e_reason ;
    String e_loc ;
    String e_fromdate;
    String e_todate;
    EditText create_fromdate;
    EditText create_todate;
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ImageButton submit=(ImageButton)findViewById(R.id.add_event);
        ImageButton clear=(ImageButton)findViewById(R.id.clear_event);
        final EditText event_name=(EditText)findViewById((R.id.event_name));
        final EditText event_reason=(EditText)findViewById((R.id.event_reason));
        final EditText event_location=(EditText)findViewById((R.id.event_location));
        create_fromdate=(EditText)findViewById(R.id.event_createfromdate);
        create_todate=(EditText)findViewById(R.id.event_createtodate);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e_name.equals("")||e_reason.equals("")||e_loc.equals("")||e_fromdate.equals("")||e_todate.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all fields!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else {
                    e_name = event_name.getText().toString();
                    e_reason = event_reason.getText().toString();
                    e_loc = event_location.getText().toString();
                    e_fromdate=create_fromdate.getText().toString();
                    e_todate=create_todate.getText().toString();

                    Intent myIntent = new Intent(CreateEventActivity.this, EventsListActivity.class);
                    startActivity(myIntent);
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event_name.setText("");
                event_reason.setText("");
                event_location.setText("");
                create_fromdate.setText("");
                create_todate.setText("");
            }
        });

        create_fromdate.setInputType(InputType.TYPE_NULL);
        create_fromdate.setOnTouchListener(listener);

        create_todate.setInputType(InputType.TYPE_NULL);
        create_todate.setOnTouchListener(listener);
    }
    OnTouchListener listener = new OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                new DatePickerDialog(CreateEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
            return false;
        }
    };
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            if(create_fromdate.isFocused()){
                updateLabel(0);
            }
            else if(create_todate.isFocused()){
                updateLabel(1);
            }
        }
    };
    private void updateLabel(int id) {
        String myFormat = "MMM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA );
        switch(id) {
            case 0:
            create_fromdate.setText(sdf.format(myCalendar.getTime()));
                break;
            case 1:
                create_todate.setText(sdf.format(myCalendar.getTime()));
                break;
        }
    }
}
