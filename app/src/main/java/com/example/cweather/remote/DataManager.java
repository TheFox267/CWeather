package com.example.cweather.remote;

import android.content.Context;

import androidx.room.Room;

import com.example.cweather.database.Event;
import com.example.cweather.database.EventBase;
import com.example.cweather.database.EventDataBase;

import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class DataManager {
    private Context context;
    private EventDataBase eventDataBase;

    public DataManager(Context context) {
        this.context = context;
        eventDataBase = Room.databaseBuilder(context, EventDataBase.class, "events.db").build();
    }

    public void addData(final DataCallBack dataCallBack, final Calendar date, final Calendar timeStart,
                        final Calendar timeEnd, final String name, final String place, final String desc,
                        final String reminder, final int color) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                Event event = new Event(date, timeStart, timeEnd, name, place, desc, reminder, color);
                eventDataBase.getEventDao().insertEvent(event);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                dataCallBack.dataAdded();
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                dataCallBack.errorAdded();
            }
        });


    }

    public void loadDataBase(final DataCallBack dataCallBack) {
        eventDataBase.getEventDao().getEventsBase().subscribeOn(Schedulers.io()).subscribe(new Consumer<List<EventBase>>() {
            @Override
            public void accept(List<EventBase> eventBases) throws Throwable {
                dataCallBack.loadEventsBase(eventBases);
            }
        });
    }

}
