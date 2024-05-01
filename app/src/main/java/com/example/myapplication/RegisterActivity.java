package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {


    ChipGroup cgCourse;
    ChipGroup cgYear;
    TextInputLayout lo_bday, lo_name,lo_address,lo_contact,lo_un,lo_pw;
    TextInputEditText et_bday, et_name,et_address,et_contact,et_un,et_pw;
    TextView tv_age;
    CustomToast toast;

    Calendar calendar = Calendar.getInstance();
    int mYear = calendar.get(Calendar.YEAR);
    int mMonth = calendar.get(Calendar.MONTH);
    int mDay = calendar.get(Calendar.DAY_OF_MONTH);
    boolean isCourseChecked = false;

    String name,address,contact,date = "",course,yearlevel,age,un,pw,pin;
    CheckBox cb;

    Integer dateYear = 0,dateMonth = 0,dateDay = 0;

    DBHelper db;

    NotificationHelper nf;

    MaterialButton btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        lo_bday = findViewById(R.id.loBday);
        lo_name = findViewById(R.id.loCustomerName);
        lo_address = findViewById(R.id.loAddress);
        lo_contact = findViewById(R.id.loPhone);
        lo_un = findViewById(R.id.loUsename);
        lo_pw = findViewById(R.id.loPassword);

        et_bday = findViewById(R.id.etBday);
        et_name = findViewById(R.id.etCustomerName);
        et_address = findViewById(R.id.etAddress);
        et_contact = findViewById(R.id.etPhone);
        et_un = findViewById(R.id.etUsername);
        et_pw = findViewById(R.id.etPassword);

        btn_register = findViewById(R.id.btnRegister);

        cb = findViewById(R.id.cb_privacy);


        tv_age = findViewById(R.id.tvAge);
        toast = new CustomToast();


        cgCourse = findViewById(R.id.cgMainCourse);
        cgYear = findViewById(R.id.cgMainYear);

        db = new DBHelper(this);
        nf = new NotificationHelper();

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    btn_register.setEnabled(true);
                }else{
                    btn_register.setEnabled(false);
                }
            }
        });

        cgCourse.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                Chip ITChip = findViewById(R.id.ITChip);
                Chip CSChip = findViewById(R.id.CSChip);
                Chip CPEChip = findViewById(R.id.CPEChip);
                TextView tv_course = findViewById(R.id.tvCourse);

                if(ITChip.isChecked()){
                    course = ITChip.getText().toString();
                    isCourseChecked = true;
                    cgYear.setVisibility(View.VISIBLE);

                    tv_course.setText("BSIT");
                }

                else if(CSChip.isChecked()){
                    course = CSChip.getText().toString();
                    isCourseChecked = true;
                    cgYear.setVisibility(View.VISIBLE);
                    tv_course.setText("BSCS");
                }

                else if(CPEChip.isChecked()){
                    course = CPEChip.getText().toString();
                    isCourseChecked = true;
                    cgYear.setVisibility(View.VISIBLE);
                    tv_course.setText("BSCPE");
                }

                else{
                    cgYear.setVisibility(View.GONE);
                    course = "";
                    yearlevel = "";
                    tv_course.setText("No courses selected");
                }
            }
        });



        cgYear.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                for(Integer i : checkedIds){
                    Chip chip = findViewById(i);
                    TextView tv_yearlevel = findViewById(R.id.tvYear);
                    yearlevel = chip.getText().toString();

                    if(yearlevel.isEmpty()){
                        tv_yearlevel.setText("Year level is not selected");
                    }else{
                        tv_yearlevel.setText(yearlevel);
                    }
                }
            }
        });



    }

    public void OpenDate(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date = year + "/" + (month + 1) + "/" + day;
                dateYear = year;
                dateMonth = month;
                dateDay = day;

                et_bday.setText(date);


               tv_age.setText(String.valueOf(GetAge(dateYear,dateMonth,dateDay)));

            }
        }, mYear,mMonth,mDay);
        Calendar calendar1 = Calendar.getInstance();
        datePickerDialog.getDatePicker().setMaxDate(calendar1.getTimeInMillis());
        datePickerDialog.show();
    }

    public void GoToLogin(View v){
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void RegisterProcess(View v){
        name = et_name.getText().toString();
        address = et_address.getText().toString();
        contact = et_contact.getText().toString();
        if(dateYear != 0 && dateMonth != 0 && dateDay != 0){
            age = String.valueOf(GetAge(dateYear, dateMonth, dateDay));
        }

        un = et_un.getText().toString();
        pw = et_pw.getText().toString();
        Random r = new Random();
        pin = String.format("%04d", r.nextInt(10003));

        if(!name.isEmpty()){

        }else{
            lo_name.setErrorEnabled(true);
            lo_name.setError("Name is missing");
        }

        if(!address.isEmpty()){

        }else{
            lo_address.setErrorEnabled(true);
            lo_address.setError("Address is missing");
        }

        if(!contact.isEmpty()) {

        }else{
            lo_contact.setErrorEnabled(true);
            lo_contact.setError("Phone number is missing");
        }

        if(!date.isEmpty()){

        }else{
            lo_bday.setErrorEnabled(true);
            lo_bday.setError("Birthday is missing");
        }

        if (!un.isEmpty()) {

        }else{
            lo_un.setErrorEnabled(true);
            lo_un.setError("Username is missing");
        }

        if(!pw.isEmpty()){

        }else{
            lo_pw.setErrorEnabled(true);
            lo_pw.setError("Password is missing");
        }

        if(name.isEmpty() || address.isEmpty() || contact.isEmpty() || date.isEmpty() || course.isEmpty() || yearlevel.isEmpty() || age.isEmpty() || un.isEmpty()|| pw.isEmpty()){
            toast.myToast(this, R.drawable.ic_failed, "Error!", "Failed to create account");
        }else{

            if(name.length() > 5 && address.length() >= 10 && contact.length() <= 11 && un.length() > 6 && un.length() < 20 && pw.length() > 8 && pw.length() < 20) {

                    if (db.CreateAccount(name, address, contact, date, course, yearlevel, age, un, pw, pin)) {
                        toast.myToast(this, R.drawable.ic_check, "Success!", "Successfully created account");
                        nf.showNotification(this, "PIN Code Registration", "Your 4-digit PIN Code: " + pin);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                            }
                        }, 3000);
                    } else {
                        toast.myToast(this, R.drawable.ic_failed, "Error!", "Failed to create account");
                    }


            }

        }









    }

    public static int GetAge(int year, int month, int day){
        Calendar dateOfBirth = Calendar.getInstance();

        dateOfBirth.set(Calendar.YEAR, year);
        dateOfBirth.set(Calendar.MONTH, month);
        dateOfBirth.set(Calendar.DAY_OF_MONTH, day);

        int ageInteger = 0;

        Calendar todayCalendar = Calendar.getInstance();
        ageInteger = todayCalendar.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
        if(todayCalendar.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH) ){
            if(todayCalendar.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH)){
                ageInteger = ageInteger - 1;
            }else if (todayCalendar.get(Calendar.MONTH) < dateOfBirth.get(Calendar.MONTH)) {

                ageInteger = ageInteger - 1;

            }

        }

        return  ageInteger;
    }


}