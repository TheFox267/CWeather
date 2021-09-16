package com.example.cweather;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.cweather.adapter.EventAdapter;
import com.example.cweather.converters.Converter;
import com.example.cweather.database.Event;
import com.example.cweather.database.EventBase;
import com.example.cweather.database.EventDataBase;
import com.example.cweather.modify.EventIndicator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TheFoxLog";
    private final Converter converter = new Converter();
    public RecyclerView recyclerView;
    public EventAdapter eventAdapter;
    EventDataBase eventDataBase;
    private FloatingActionButton btnAdd;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventDataBase = EventDataBase.getInstance(this);
        initWidgets();
        initViewModel();
        initRecyclerView();
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
        // Смена даты
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                eventDataBase.getEventDao().getEventsByDate(eventDay.getCalendar()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<Event>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, d.toString());
                    }

                    @Override
                    public void onSuccess(@NonNull List<Event> events) {
                        try {
                            eventAdapter.setData(events);
                            recyclerView.setAdapter(eventAdapter);
                        } catch (NullPointerException exception) {
                            Log.e(TAG, exception.getMessage());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, e.getMessage());

                    }
                });
            }
        });

    }

    private void initViewModel() {
        eventDataBase.getEventDao().getEventsBase().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<EventBase>>() {
            @Override
            public void accept(List<EventBase> eventBases) throws Throwable {
                if (eventBases.size() > 0) {
                    List<EventDay> eventDays = new ArrayList<>();
                    Converter converter = new Converter();
                    for (EventBase eventBase : eventBases) {
                        EventIndicator eventIndicator = new EventIndicator(eventBase.date, new ColorDrawable(eventBase.color));
                        eventDays.add(eventIndicator);
                    }
                    calendarView.setEvents(eventDays);
                    Log.d(TAG, "CalendarView обновлён");
                }

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
        // RecyclerView
        recyclerView = findViewById(R.id.recView);
        // EventDataBase
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
}
