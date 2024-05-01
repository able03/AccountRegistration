package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder>
{
    private Context context;
    private List<CustomerModel> customerList;

    public CustomerAdapter(){}

    public CustomerAdapter(Context context) {
        this.context = context;
    }

    public void setCustomerList(List<CustomerModel> customerList) {
        this.customerList = customerList;
        notifyDataSetChanged();
    }

    public void filteredList(List<CustomerModel> customerList)
    {
        this.customerList = customerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.customer_rv_layout, parent, false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.MyViewHolder holder, int position) {
        int pos = position;
        int id =customerList.get(pos).getId();
        String name =customerList.get(pos).getName();
        String course =customerList.get(pos).getCourse();
        String year =customerList.get(pos).getYear();

        holder.id.setText(String.valueOf(id));
        holder.name.setText(name);
        holder.course.setText(course);
        holder.year.setText(year);


        holder.cv.setOnClickListener(click -> {
            DBHelper dbHelper = new DBHelper(context);
            Cursor cursor = dbHelper.FindDetailsWithId(id);
            if(cursor.moveToFirst())
            {
                String address = cursor.getString(2);
                String contact = cursor.getString(3);
                String birthdate = cursor.getString(4);
                String age = cursor.getString(7);
                String username = cursor.getString(8);
                String password = cursor.getString(9);
                String pin = cursor.getString(10);

                new SearchStaticModel(id, name, address, contact, birthdate, course, year, age, username, password, pin);
                Intent intent = new Intent(context, SearchDetailsActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView id, name, course, year;
        private CardView cv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvIdSearch);
            name = itemView.findViewById(R.id.tvNameSearch);
            course = itemView.findViewById(R.id.tvCourseSearch);
            year = itemView.findViewById(R.id.tvYearSearch);

            cv = itemView.findViewById(R.id.cvSearch);
        }
    }
}
