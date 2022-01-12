package com.example.karwashandautodetailing;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;



public class BookingActivity extends AppCompatActivity {

    private Button btnSubmit;
    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;
    private EditText date, time, location;

    DatePickerDialog datepicker;
    TimePickerDialog timepicker;

    CheckBox buffing, detailing, enginewash, headlights, minorscratch, normalwash, underbody, watermarks;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    BookingInfo bookingInfo;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        databaseReference = firebaseDatabase.getInstance().getReference().child("Booking");


        date = findViewById(R.id.editText1);
        date.setInputType(InputType.TYPE_NULL);


        time = findViewById(R.id.editText2);
        time.setInputType(InputType.TYPE_NULL);

        location = findViewById(R.id.editText3);
        location.setInputType(InputType.TYPE_NULL);

        buffing = findViewById(R.id.buffing);
        detailing = findViewById(R.id.detailing);
        enginewash = findViewById(R.id.enginewash);
        headlights = findViewById(R.id.headlightscleaning);
        minorscratch = findViewById(R.id.minorscratchesremoval);
        normalwash = findViewById(R.id.normalwash);
        underbody = findViewById(R.id.underbodywash);
        watermarks = findViewById(R.id.watermarksremoval);
        String d1 = "Buffing";
        String d2 = "Detailing";
        String d3 = "Engine Wash";
        String d4 = "Headlights Cleaning";
        String d5 = "Minor Scratches Removal";
        String d6 = "Normal Wash";
        String d7 = "Under-Body Wash";
        String d8 = "Watermarks Removal";


        String apiKey = getString(R.string.api_key);
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), apiKey);
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    i = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {

            }
        });


        bookingInfo = new BookingInfo();
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String datetext = date.getText().toString();
                String timetext = time.getText().toString();
                String locationtext = location.getText().toString();

                if (TextUtils.isEmpty(datetext) && TextUtils.isEmpty(timetext) && TextUtils.isEmpty(locationtext)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(BookingActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebase(datetext, timetext, locationtext);
                }

                if (buffing.isChecked()) {
                    bookingInfo.setServiceOne(d1);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }
                if (detailing.isChecked()) {
                    bookingInfo.setServiceTwo(d2);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }
                if (enginewash.isChecked()) {
                    bookingInfo.setServiceThree(d3);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }
                if (headlights.isChecked()) {
                    bookingInfo.setServiceFour(d4);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }
                if (minorscratch.isChecked()) {
                    bookingInfo.setServiceFive(d5);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }
                if (normalwash.isChecked()) {
                    bookingInfo.setServiceSix(d6);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }
                if (underbody.isChecked()) {
                    bookingInfo.setServiceSeven(d7);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }
                if (watermarks.isChecked()) {
                    bookingInfo.setServiceEight(d8);
                    databaseReference.child(String.valueOf(i + 1)).setValue(bookingInfo);
                } else {
                }

            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                datepicker = new DatePickerDialog(BookingActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);


                            }
                        }, year, month, day);
                datepicker.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timepicker = new TimePickerDialog(BookingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                time.setText(sHour + ":" + sMinute);

                            }
                        }, hour, minutes, true);
                timepicker.show();
            }
        });



                location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Set the fields to specify which types of place data to
                        // return after the user has made a selection.
                        List<Place.Field> field = Arrays.asList(Place.Field.ID, Place.Field.ADDRESS);

                        // Start the autocomplete intent.
                        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, field)
                                .build(BookingActivity.this);
                        //start activity result
                        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //When success initialize place
                Place place = Autocomplete.getPlaceFromIntent(data);

                //set address on edittext
                location.setText(place.getAddress());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                //Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    private void addDatatoFirebase(String datetext, String timetext, String locationtext) {
        // below 3 lines of code is used to set
        // data in our object class.

        bookingInfo.setBookingDate(datetext);
        bookingInfo.setBookingTime(timetext);
        bookingInfo.setBookingLocation(locationtext);
        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(bookingInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(BookingActivity.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(BookingActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}










