package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    String un, pw;
    TextInputEditText et_un, et_pw;

    TextInputLayout lo_un, lo_pw;

    TextView et1, et2, et3, et4;
    DBHelper db;

    CustomToast toast;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        et_un = findViewById(R.id.etLoginUsername);
        et_pw = findViewById(R.id.etLoginPassword);

        lo_un = findViewById(R.id.loLoginUsername);
        lo_pw = findViewById(R.id.loLoginPassword);

        toast = new CustomToast();

        et_un.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lo_un.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_pw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                lo_pw.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void LoginProcess(View view) {
        un = et_un.getText().toString();
        pw = et_pw.getText().toString();

        if(!un.isEmpty()){

        }else{
            lo_un.setErrorEnabled(true);
            lo_un.setError("Username is missing");
        }

        if(!pw.isEmpty()){

        }else{
            lo_pw.setErrorEnabled(true);
            lo_pw.setError("Password is missing");
        }

        if(un.isEmpty() || pw.isEmpty()){

        }else{
            Cursor c = db.FindAccount(un,pw);
            if(c.getCount() > 0){
                c.moveToFirst();
                Integer id = c.getInt(0);
                String name = c.getString(1);
                String address = c.getString(2);
                String contact = c.getString(3);
                String birthday = c.getString(4);
                String course = c.getString(5);
                String year = c.getString(6);
                String age = c.getString(7);
                String PIN = c.getString(8);

                new CustomerStaticModel(id,name,address,contact,birthday,course,year,age,un,pw,PIN);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }else{
                toast.myToast(LoginActivity.this, R.drawable.ic_failed, "Failed!", "Account not found!");
            }
        }




    }

    public void GoToRegister(View view){
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }


    public void ShowAlertDialog(View view)
    {
        showDialog();
    }

    private void showDialog()
    {
        View view = LayoutInflater.from(this).inflate(R.layout.pin_layout, null);

        et1 = view.findViewById(R.id.et1);
        et2 = view.findViewById(R.id.et2);
        et3 = view.findViewById(R.id.et3);
        et4 = view.findViewById(R.id.et4);

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 1)
                {
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 1)
                {
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 1)
                {
                    et4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("Enter your pin code:");
        builder.setPositiveButton("submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            String pinCode = et1.getText().toString() + et2.getText().toString() + et3.getText().toString() + et4.getText().toString();
            if(pinCode.isEmpty()){
                toast.myToast(LoginActivity.this, R.drawable.ic_failed, "Failed!", "Pin code is empty!");
            }else{
                Cursor c = db.FindPIN(et1.getText().toString() + et2.getText().toString() + et3.getText().toString() + et4.getText().toString());
                if(c.getCount() > 0){
                    c.moveToFirst();
                    Integer id = c.getInt(0);
                    String name = c.getString(1);
                    String address = c.getString(2);
                    String contact = c.getString(3);
                    String birthday = c.getString(4);
                    String course = c.getString(5);
                    String year = c.getString(6);
                    String age = c.getString(7);
                    String PIN = c.getString(8);


                    new CustomerStaticModel(id,name,address,contact,birthday,course,year,age,un,pw,PIN);
                    toast.myToast(LoginActivity.this, R.drawable.ic_check, "Success!", "PIN Code Verified!");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent a = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(a);
                        }
                    }, 2000);

                }else{
                    toast.myToast(LoginActivity.this, R.drawable.ic_failed, "Failed!", "PIN Code not registered!");
                }
            }

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }



}