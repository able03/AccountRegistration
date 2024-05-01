package com.example.myapplication;

public class CustomerModel
{



        private  Integer id;
        private  String name;
        private  String address;
        private  String contact;
        private  String birthday;
        private  String course;
        private  String year;
        private  String age;
        private  String username;
        private  String password;
        private  String pin;



        public CustomerModel(Integer id, String name, String address, String contact, String birthday, String course, String year, String age, String username, String password, String pin) {
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

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getContact() {
            return contact;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getCourse() {
            return course;
        }

        public String getYear() {
            return year;
        }

        public String getAge() {
            return age;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getPin() {
            return pin;
        }
    }


