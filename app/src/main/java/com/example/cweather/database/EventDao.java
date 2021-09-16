package com.example.cweather.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Calendar;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface EventDao {
    @Query("SELECT dateCalendar, color FROM event_table")
    Flowable<List<EventBase>> getEventsBase();

    @Insert
    Completable insertEvent(Event event);


    @Query("SELECT * FROM event_table WHERE dateCalendar = (:dateSelected)")
    Single<List<Event>> getEventsByDate(String dateSelected);

}
