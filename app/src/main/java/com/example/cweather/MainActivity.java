package com.example.cweather;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.cweather.database.EventBase;
import com.example.cweather.modify.EventIndicator;
import com.example.cweather.remote.DataCallBack;
import com.example.cweather.remote.DataManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCallBack {
    private static final String TAG = "TheFoxLogs";
    private FloatingActionButton btnAdd;
    private CalendarView calendarView;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        // DataManager
        dataManager = new DataManager(MainActivity.this);
        dataManager.loadDataBase(MainActivity.this);
        // Floating action button
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

    /**
     * Инициализовать все объекты активити
     */
    private void initWidgets() {
        // Floating Action Bar
        btnAdd = findViewById(R.id.btnAdd);
        // Calendar View
        calendarView = findViewById(R.id.calendarView);
    }

    @Override
    public void dataAdded() {

    }

    @Override
    public void errorAdded() {

    }

    @Override
    public void loadEventsBase(List<EventBase> events) {
        List<EventDay> eventDays = new ArrayList<>();
        for (EventBase eventBase : events) {
            EventIndicator eventIndicator = new EventIndicator(eventBase.date, new ColorDrawable(eventBase.color));
            eventDays.add(eventIndicator);
        }
        calendarView.setEvents(eventDays);
    }
}
