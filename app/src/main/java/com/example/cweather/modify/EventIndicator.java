package com.example.cweather.modify;

import android.graphics.drawable.Drawable;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;

public class EventIndicator extends EventDay {
    private Calendar startTime, endTime;
    private String name, place, desc, reminder;

    public EventIndicator(Calendar day) {
        super(day);
    }

    public EventIndicator(Calendar day, int drawable) {
        super(day, drawable);
    }

    public EventIndicator(Calendar day, Drawable drawable) {
        super(day, drawable);
    }

    public EventIndicator(Calendar day, int drawable, int labelColor) {
        super(day, drawable, labelColor);
    }

    public EventIndicator(Calendar day, Drawable drawable, int labelColor) {
        super(day, drawable, labelColor);
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
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
}
