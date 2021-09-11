package com.example.cweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TheFoxLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Календарь
        List<EventDay> events = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.DATE, 10);
        events.add(new EventDay(calendar, R.drawable.sample_icon));
        events.add(new EventDay(calendar1, R.color.black));
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setEvents(events);

        // Float action button
        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEvent.class);
                long selectedDate = calendarView.getFirstSelectedDate().getTimeInMillis();
                intent.putExtra("selectedDate", selectedDate);
                startActivity(intent);
            }
        });


    }
}