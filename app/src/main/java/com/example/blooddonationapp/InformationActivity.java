package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.widget.TextView;

import java.util.Calendar;

import static java.net.Proxy.Type.HTTP;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        String infoText = "A blood donation occurs when a person voluntarily has blood drawn and used for transfusions and/or made into biopharmaceutical medications by a process called fractionation (separation of whole blood components). \n\n" +
                "Donation may be of whole blood, or of specific components directly (apheresis).\n\n" +
                "Blood banks often participate in the collection process as well as the procedures that follow it.";

        TextView infoTextView = (TextView) findViewById(R.id.DummyTextView);
        infoTextView.setText(infoText);


        TextView moreInfo = (TextView)findViewById(R.id.infoTxtView);
        moreInfo.setOnClickListener(v -> {

            Uri webpage = Uri.parse("https://en.wikipedia.org/wiki/Blood_donation");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        });


        TextView location = (TextView)findViewById(R.id.locationTxtView);
        location.setOnClickListener(v -> {

            Uri geo = Uri.parse("geo:23°47'01.8\"N 90°22'28.8\"E");
            Intent geoIntent = new Intent(Intent.ACTION_VIEW, geo);
            startActivity(geoIntent);
        });


        TextView mail = (TextView)findViewById(R.id.mailTxtView);
        mail.setOnClickListener(v -> {

            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            emailIntent.setType("text/plain");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"hosp@green.edu.com"}); // recipients
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reserve Appointment");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "I want to reserve an appointment at the hospital.");
            startActivity(emailIntent);
        });


        TextView calendar = (TextView)findViewById(R.id.calendarTxtView);
        calendar.setOnClickListener(v -> {

            Intent calendarIntent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);
            Calendar beginTime = Calendar.getInstance();
            beginTime.set(2021, 5, 15, 7, 30);
            Calendar endTime = Calendar.getInstance();
            endTime.set(2021, 5, 15, 10, 30);
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
            calendarIntent.putExtra(CalendarContract.Events.TITLE, "Hospital Appointment");
            calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Green University Hospital");

            startActivity(calendarIntent);
        });

    }
}