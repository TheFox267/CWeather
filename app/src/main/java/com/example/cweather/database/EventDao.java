package com.example.cweather.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface EventDao {
    @Query("SELECT date, color FROM event_table")
    Flowable<List<EventBase>> getEventsBase();

    @Insert
    void insertEvent(Event event);

}
