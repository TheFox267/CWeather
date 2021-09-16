package com.example.cweather.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "event_table")
public class Event {
    @PrimaryKey(autoGenerate = true)
    public int eventId;

    @ColumnInfo(name = "dateCalendar")
    public String dateCalendar;

    @ColumnInfo(name = "timeStart")
    public String timeStart;

    @ColumnInfo(name = "timeEnd")
    public String timeEnd;

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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDateCalendar() {
        return dateCalendar;
    }

    public void setDateCalendar(String dateCalendar) {
        this.dateCalendar = dateCalendar;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
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

    public Event(String dateCalendar, String timeStart, String timeEnd, String name, String place, String desc, String reminder, int color) {
        this.dateCalendar = dateCalendar;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.name = name;
        this.place = place;
        this.desc = desc;
        this.reminder = reminder;
        this.color = color;
    }
}


