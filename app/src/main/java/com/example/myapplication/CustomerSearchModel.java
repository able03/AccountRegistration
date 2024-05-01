package com.example.myapplication;

public class CustomerSearchModel
{
    private int id;
    private String name;
    private String course;
    private String year;

    public CustomerSearchModel(int id, String name, String course, String year) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public String getYear() {
        return year;
    }
}
