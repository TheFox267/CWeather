package com.example.cweather.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cweather.converters.Converter;

import java.util.Calendar;

@Entity(tableName = "event_table")
public class Event {
    @PrimaryKey(autoGenerate = true)
    public int eventId;

    @ColumnInfo(name = "date")
    @TypeConverters({Converter.class})
    public Calendar date;

    @ColumnInfo(name = "timeStart")
    @TypeConverters({Converter.class})
    public Calendar timeStart;

    @ColumnInfo(name = "timeEnd")
    @TypeConverters({Converter.class})
    public Calendar timeEnd;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "place")
    public String place;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "reminder")
    public String reminder;

    @ColumnInfo(name = "color")
    public int color;

    public Event(Calendar date, Calendar timeStart, Calendar timeEnd, String name, String place, String desc, String reminder, int color) {
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
        this.desc = desc;
        this.reminder = reminder;
        this.color = color;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Calendar timeStart) {
        this.timeStart = timeStart;
    }

    public Calendar getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Calendar timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}


