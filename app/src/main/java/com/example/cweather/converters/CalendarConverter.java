package com.example.cweather.converters;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarConverter {
    @TypeConverter
    public long fromCalendar(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(long data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(data);
        return calendar;
    }

}
