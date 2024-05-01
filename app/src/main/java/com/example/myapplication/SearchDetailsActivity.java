package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SearchDetailsActivity extends AppCompatActivity {

    DBHelper db;
    CustomerStaticModel csm;

    TextView tv_name,tv_address,tv_contact,tv_birthdate,tv_course,tv_year,tv_age;
    String name, address, contact, birthdate,course,year,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_details);





        db = new DBHelper(this);

        tv_name = findViewById(R.id.tvDetailsName1);
        tv_address = findViewById(R.id.tvDetailsAddress1);
        tv_contact = findViewById(R.id.tvDetailsContact1);
        tv_birthdate = findViewById(R.id.tvDetailsBday1);
        tv_course = findViewById(R.id.tvDetailsCourse1);
        tv_year = findViewById(R.id.tvDetailsYear1);
        tv_age = findViewById(R.id.tvDetailsAge1);



        tv_name.setText(SearchStaticModel.getName());
        tv_address.setText(SearchStaticModel.getAddress());
        tv_contact.setText(SearchStaticModel.getContact());
        tv_birthdate.setText(SearchStaticModel.getBirthday());
        tv_course.setText(SearchStaticModel.getCourse());
        tv_year.setText(SearchStaticModel.getYear());
        tv_age.setText(SearchStaticModel.getAge() + " years old");

        Cursor c = db.FindDetailsWithName("Ang");
        if(c.getCount() > 0){
            c.moveToFirst();
            String address = c.getString(2);
            Toast.makeText(this, "Address: " + address, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Name not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void GoBack(View view)
    {
        Intent intent = new Intent(SearchDetailsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}