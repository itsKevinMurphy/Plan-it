package com.plan_it.mobile.plan_it;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Button submit=(Button)findViewById(R.id.add_event);
        Button clear=(Button)findViewById(R.id.clear_event);
        final EditText event_name=(EditText)findViewById((R.id.event_name));
        final EditText event_reason=(EditText)findViewById((R.id.event_reason));
        final EditText event_location=(EditText)findViewById((R.id.event_location));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e_name = event_name.getText().toString();
                String e_reason = event_reason.getText().toString();
                String e_loc = event_location.getText().toString();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event_name.setText("");
                event_reason.setText("");
                event_location.setText("");
            }
        });
    }

}
