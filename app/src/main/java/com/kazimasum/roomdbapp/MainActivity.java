package com.kazimasum.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
   EditText t1,t2,t3;
   TextView lbl;
   Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);

        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        lbl=findViewById(R.id.lbl);

         b1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                         AppDatabase.class, "room_db").allowMainThreadQueries().build();
                 UserDao userDao = db.userDao();
                 Boolean check=userDao.is_exist(Integer.parseInt(t1.getText().toString()));
                 if(check==false) {
                     userDao.insertrecord(new User(Integer.parseInt(t1.getText().toString()), t2.getText().toString(), t3.getText().toString()));
                     t1.setText("");
                     t2.setText("");
                     t3.setText("");
                     lbl.setText("Inserted Successfully");
                 }
                 else
                 {
                     t1.setText("");
                     t2.setText("");
                     t3.setText("");
                     lbl.setText("Record is existing");
                 }
             }
         });

         b2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
               startActivity(new Intent(getApplicationContext(), fetchdata.class));
             }
         });

    }


}