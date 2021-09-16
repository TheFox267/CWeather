package com.example.cweather.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cweather.converters.Converter;

@Database(entities = Event.class, version = 1, exportSchema = false)
public abstract class EventDataBase extends RoomDatabase {
    private static EventDataBase INSTANCE;

    public static synchronized EventDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), EventDataBase.class, "events.db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public abstract EventDao getEventDao();
}
