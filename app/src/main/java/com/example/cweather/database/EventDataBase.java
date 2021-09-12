package com.example.cweather.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Event.class, version = 1)
public abstract class EventDataBase extends RoomDatabase {
    public abstract EventDao getEventDao();
}
