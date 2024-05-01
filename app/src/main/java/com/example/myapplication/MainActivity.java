package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    CustomerStaticModel csm;

    TextView tv_name,tv_address,tv_contact,tv_birthdate,tv_course,tv_year,tv_age;
    String name, address, contact, birthdate,course,year,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        db = new DBHelper(this);

        tv_name = findViewById(R.id.tvDetailsName);
        tv_address = findViewById(R.id.tvDetailsAddress);
        tv_contact = findViewById(R.id.tvDetailsContact);
        tv_birthdate = findViewById(R.id.tvDetailsBday);
        tv_course = findViewById(R.id.tvDetailsCourse);
        tv_year = findViewById(R.id.tvDetailsYear);
        tv_age = findViewById(R.id.tvDetailsAge);



        tv_name.setText(CustomerStaticModel.getName());
        tv_address.setText(CustomerStaticModel.getAddress());
        tv_contact.setText(CustomerStaticModel.getContact());
        tv_birthdate.setText(CustomerStaticModel.getBirthday());
        tv_course.setText(CustomerStaticModel.getCourse());
        tv_year.setText(CustomerStaticModel.getYear());
        tv_age.setText(CustomerStaticModel.getAge() + " years old");

        Cursor c = db.FindDetailsWithName("Ang");
        if(c.getCount() > 0){
            c.moveToFirst();
            String address = c.getString(2);
            Toast.makeText(this, "Address: " + address, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Name not found", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, CustomerStaticModel.getPin(), Toast.LENGTH_SHORT).show();
    }

    public void GoLogout(View v){
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public void GoSearch(View view)
    {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }
}