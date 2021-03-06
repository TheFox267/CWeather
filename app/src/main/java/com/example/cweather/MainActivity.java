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
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
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
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    // Константы
    public static final String TAG = "TheFoxLog";
    public static final Converter converter = new Converter();
    private static final String FORMAT = Converter.FORMAT;
    private static final Locale LOCALE = Converter.LOCALE;
    // Переменные
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private EventDataBase eventDataBase;
    private FloatingActionButton btnAdd;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        initCalendarView();
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
        calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                eventAdapter.clearEventList();
            }
        });
        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                eventAdapter.clearEventList();
            }
        });
        // Смена даты
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                eventDataBase.getEventDao().getEventsByDate(converter.fromCalendarToString(eventDay.getCalendar())).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<Event>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d(TAG, d.toString());
                    }

                    @Override
                    public void onSuccess(@NonNull List<Event> events) {
                        try {
                            eventAdapter.clearEventList();
                            eventAdapter.setEventList(events);
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

    /**
     * Создать "наблюдателя" за базой данных. Когда пользователь создаёт новое событие, то обновляется индикация на
     * календаре
     */
    private void initCalendarView() {
        eventDataBase.getEventDao().getEventsBase().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<EventBase>>() {
            @Override
            public void accept(List<EventBase> eventBases) throws Throwable {
                if (eventBases.size() > 0) {
                    List<EventDay> eventDays = new ArrayList<>();
                    for (EventBase eventBase : eventBases) {
                        EventIndicator eventIndicator = new EventIndicator(converter.fromStringToCalendar(eventBase.dateCalendar),
                                new ColorDrawable(eventBase.color));
                        eventDays.add(eventIndicator);
                    }
                    calendarView.setEvents(eventDays);
                    Log.d(TAG, "CalendarView обновлён");
                }
            }
        });
    }

    /**
     * Определить все объекты данного окна
     */
    private void initWidgets() {
        // Floating Action Bar
        btnAdd = findViewById(R.id.btnAdd);
        // Calendar View
        calendarView = findViewById(R.id.calendarView);
        // RecyclerView
        recyclerView = findViewById(R.id.recView);
        // EventDataBase
        eventDataBase = EventDataBase.getInstance(this);
        // Test


    }

    /**
     * Установить параметры для RecyclerView
     */
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        eventAdapter = new EventAdapter();
        recyclerView.setAdapter(eventAdapter);
    }
}
