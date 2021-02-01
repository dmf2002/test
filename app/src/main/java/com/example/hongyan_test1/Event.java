package com.example.hongyan_test1;

import org.w3c.dom.Text;

public class Event {
    private String years;
    private String monthday;
    private String title;
    private String desc1;
    private String type;
    public Event(String years,String monthday,String title,String desc1,String type)
    {
        this.years=years;
        this.monthday=monthday;
        this.title=title;
        this.desc1=desc1;
        this.type=type;
    }

    public String getMonthday() {
        return monthday;
    }

    public String getType() {
        return type;
    }

    public String getYears() {
        return years;
    }

    public String getDesc1() {
        return desc1;
    }

    public String getTitle() {
        return title;
    }
}
