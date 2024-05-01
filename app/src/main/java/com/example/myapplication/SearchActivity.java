package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private CustomerAdapter adapter;
    private List<CustomerModel> customerList;
    private List<CustomerModel> searchList;
    private TextView tv_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initValues();
        getListData();

        setSearchListener();
    }

    private void initValues()
    {
        searchView = findViewById(R.id.svSearch);
        recyclerView = findViewById(R.id.rvSearch);

        customerList = new ArrayList<>();
        tv_record = findViewById(R.id.tvRecordFound);

        adapter = new CustomerAdapter(this);
    }

    private void setSearchListener()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                   filteredList(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty())
                {
                    clearSearchList();
                }
                return true;
            }
        });
    }


    private void clearSearchList()
    {
        searchList.clear();
        adapter.setCustomerList(searchList);
        tv_record.setText(String.valueOf(0));
        populateRV();
    }

    private void filteredList(String query)
    {
        searchList = new ArrayList<>();
        for(int i=0; i<customerList.size(); i++)
        {
            if(customerList.get(i).getName().toLowerCase().contains(query.toLowerCase()))
            {
                searchList.add(customerList.get(i));
            }

        }


        adapter.setCustomerList(searchList);

        if(!searchList.isEmpty())
        {
            tv_record.setText(String.valueOf(searchList.size()));
        }
        else
        {
            tv_record.setText(String.valueOf(0));

        }

        populateRV();
    }

    private void getListData() {
        DBHelper dbHelper = new DBHelper(SearchActivity.this);
        Cursor cursor = dbHelper.GetAllData();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            String contact = cursor.getString(3);
            String bdate = cursor.getString(4);
            String course = cursor.getString(5);
            String year = cursor.getString(6);
            String age = cursor.getString(7);
            String user = cursor.getString(8);
            String pass = cursor.getString(9);
            String pin = cursor.getString(10);

            customerList.add(new CustomerModel(id, name, address, contact, bdate, course, year, age, user, pass, pin));
        }


    }

    private void populateRV()
    {

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}