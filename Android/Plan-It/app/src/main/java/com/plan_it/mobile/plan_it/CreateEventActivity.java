package com.plan_it.mobile.plan_it;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateEventActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    public String token;
    String base64Image;
    byte[] imageByte;
    String e_name;
    String e_reason;
    String e_loc;
    String e_fromdate;
    String e_todate;
    String e_fromTime;
    String e_toTime;
    EditText create_fromdate;
    EditText create_todate;
    EditText create_fromtime;
    EditText create_totime;
    Calendar myCalendar = Calendar.getInstance();

    ImageView viewImage;

    private int hour;
    private int minute;

    static final int TIME_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Button submit = (Button) findViewById(R.id.add_event);
        Button clear = (Button) findViewById(R.id.clear_event);
        final EditText event_name = (EditText) findViewById((R.id.event_name));
        final EditText event_reason = (EditText) findViewById((R.id.event_reason));
        final EditText event_location = (EditText) findViewById((R.id.event_location));
        create_fromdate = (EditText) findViewById(R.id.event_createfromdate);
        create_todate = (EditText) findViewById(R.id.event_createtodate);
        create_fromtime=(EditText) findViewById(R.id.event_createfromtime);
        create_totime=(EditText) findViewById(R.id.event_createtotime);



        create_fromtime.setInputType(InputType.TYPE_NULL);
        create_fromtime.setOnTouchListener(lister);

        create_totime.setInputType(InputType.TYPE_NULL);
        create_totime.setOnTouchListener(listen);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e_name = event_name.getText().toString();
                e_reason = event_reason.getText().toString();
                e_loc = event_location.getText().toString();
                e_fromdate = create_fromdate.getText().toString();
                e_todate = create_todate.getText().toString();
                e_fromTime = create_fromtime.getText().toString();
                e_toTime = create_totime.getText().toString();


                if (e_name.equals("") || e_reason.equals("") || e_loc.equals("") || e_fromdate.equals("")
                        || e_todate.equals("")|| e_toTime.equals("")|| e_fromTime.equals("")) {

                    Toast toast = Toast.makeText(getApplicationContext(), "Please fill in all fields!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    try {
                        createEvent();

                    }
                    catch (JSONException ex){
                        ex.printStackTrace();
                    }
                    Intent eIntent = new Intent(CreateEventActivity.this, EventsListActivity.class);
                    startActivity(eIntent);
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

    OnTouchListener listener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                new DatePickerDialog(CreateEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
            return false;
        }
    };

    OnTouchListener lister = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                showDialog(TIME_DIALOG_ID);
            }
            return false;
        }
    };
    OnTouchListener listen = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                showDialog(TIME_DIALOG_ID);
            }
            return false;
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                // set time picker as current time
                return new TimePickerDialog(CreateEventActivity.this,
                        timePickerListener, hour, minute, false);

        }
        return null;
    }
    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour,
                                      int selectedMinute ) {
                    Time tme = new Time(selectedHour,selectedMinute,0);
                    Format formatter;
                    formatter = new SimpleDateFormat("h:mm a");
                    formatter.format(tme);


                    if (create_fromtime.isFocused()) {
                        create_fromtime.setText(formatter.format(tme));
                    }
                    if (create_totime.isFocused()) {
                        create_totime.setText(formatter.format(tme));
                    }
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

            if (create_fromdate.isFocused()) {
                updateLabel(0);
            } else if (create_todate.isFocused()) {
                updateLabel(1);
            }
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_event, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_friends_list)
        {
            Intent intent = new Intent(this, FriendsListActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_add_friends)
        {
            Intent intent = new Intent(this, AddFriendActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_event_list)
        {
            Intent intent = new Intent(this, EventsListActivity.class);
            startActivity(intent);
        }
        if (id == R.id.action_logout)
        {
            LoginActivity.token = null;
            LoginActivity.userID = 0;
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void updateLabel(int id) {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
        switch (id) {
            case 0:
                create_fromdate.setText(sdf.format(myCalendar.getTime()));
                break;
            case 1:
                create_todate.setText(sdf.format(myCalendar.getTime()));
                break;
        }
    }
    public void imageOptions(View c) {
        viewImage = (ImageView) findViewById(R.id.create_eventphoto);
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateEventActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            1);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                Bitmap scaled = Bitmap.createScaledBitmap(thumbnail, 256, 256, true);

                viewImage.setImageBitmap(scaled);
                imageByte = bytes.toByteArray();
                scaled.compress(Bitmap.CompressFormat.JPEG, 10, bytes);
                base64Image = Base64.encodeToString(imageByte, Base64.NO_WRAP);


            } else if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
                        null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);

                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                Bitmap d = new BitmapDrawable(getApplicationContext().getResources() , selectedImagePath).getBitmap();
                Bitmap scaled = Bitmap.createScaledBitmap(d,140, 150, true);
                scaled.compress(Bitmap.CompressFormat.JPEG, 10, bytes);

                imageByte = bytes.toByteArray();
                base64Image = Base64.encodeToString(imageByte, Base64.NO_WRAP);
                viewImage.setImageBitmap(scaled);
            }
        }
    }


    public void createEvent() throws JSONException {
        RequestParams jdata = new RequestParams();
        jdata.put("what",e_name);
        jdata.put("why", e_reason);
        jdata.put("where", e_loc);
        jdata.put("when",e_fromdate);
        jdata.put("endDate", e_todate);
        jdata.put("fromTime",e_fromTime);
        jdata.put("toTime", e_toTime);
        jdata.put("picture", base64Image);

       // Token tokenClass = new Token();
        token = LoginActivity.token;

        RestClient.post("events", jdata, token, new JsonHttpResponseHandler() {
            public void onSuccess(String response) {
                JSONObject res;
                try {
                    res = new JSONObject(response);
                    Log.d("debug", res.getString("some_key")); // this is how you get a value out


                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
