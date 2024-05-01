package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast extends AppCompatActivity {


   private ImageView iv_icon;
   private TextView tv_status, tv_message;
   private CardView cv_toast;
   /*public void myToast(Context context, int imgRID, String status,
                       String message, int coloRID)
   {
      View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);


      iv_icon = view.findViewById(R.id.ivToastIcon);
      tv_status = view.findViewById(R.id.tvToastStatus);
      tv_message = view.findViewById(R.id.tvToastMessage);
      cv_toast = view.findViewById(R.id.cvToast);


      iv_icon.setImageResource(imgRID);
      tv_status.setText(status);
      tv_message.setText(message);
      cv_toast.setCardBackgroundColor(coloRID);

      Toast toast = new Toast(context);
      toast.setView(view);
      toast.setDuration(Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0,0);
      toast.show();
   }*/

   public void myToast(Context context, int imgRID, String status,
                       String message)
   {
      View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);


      iv_icon = view.findViewById(R.id.ivToastIcon);
      tv_status = view.findViewById(R.id.tvToastStatus);
      tv_message = view.findViewById(R.id.tvToastMessage);

      iv_icon.setImageResource(imgRID);
      tv_status.setText(status);
      tv_message.setText(message);

      Toast toast = new Toast(context);
      toast.setView(view);
      toast.setDuration(Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0,0);
      toast.show();
   }
}