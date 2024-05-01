package com.example.myapplication;

import java.util.Optional;

public class SearchStaticModel {
    private static Integer id;
    private static String name;
    private static String address;
    private static String contact;
    private static String birthday;
    private static String course;
    private static String year;
    private static String age;
    private static String username;
    private static String password;
    private static String pin;



    public SearchStaticModel(Integer id, String name, String address, String contact, String birthday, String course, String year, String age, String username, String password, String pin) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.birthday = birthday;
        this.course = course;
        this.year = year;
        this.age = age;
        this.username = username;
        this.password = password;
        this.pin = pin;
    }

    public static Integer getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getAddress() {
        return address;
    }

    public static String getContact() {
        return contact;
    }

    public static String getBirthday() {
        return birthday;
    }

    public static String getCourse() {
        return course;
    }

    public static String getYear() {
        return year;
    }

    public static String getAge() {
        return age;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }


}
