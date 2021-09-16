package com.example.cweather.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Event.class, version = 1, exportSchema = false)
public abstract class EventDataBase extends RoomDatabase {
    private static EventDataBase INSTANCE;

    public static synchronized EventDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), EventDataBase.class, "events.db").build();
        }
        return INSTANCE;
    }

    public abstract EventDao getEventDao();


}
