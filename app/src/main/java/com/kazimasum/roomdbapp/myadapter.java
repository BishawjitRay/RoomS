package com.kazimasum.roomdbapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder>
{
    List<User> users;

    public myadapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull myadapter.myviewholder holder, int position) {
          holder.recid.setText(String.valueOf(users.get(position).getUid()));
          holder.recfname.setText(users.get(position).getFirstName());
          holder.reclname.setText(users.get(position).getLastName());
          holder.delbtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  AppDatabase db = Room.databaseBuilder(holder.recid.getContext(),
                          AppDatabase.class, "room_db").allowMainThreadQueries().build();
                  UserDao userDao = db.userDao();
                  // this is to delete the record from room database
                  userDao.deleteById(users.get(position).getUid());
                  // this is to delete the record from Array List which is the source of recview data
                  users.remove(position);

                  //update the fresh list of ArrayList data to recview
                  notifyDataSetChanged();
              }
          });
          holder.edbtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent=new Intent(new Intent(holder.edbtn.getContext(),updatedata.class));
                  intent.putExtra("uid",String.valueOf(users.get(position).getUid()));
                  intent.putExtra("ufname",users.get(position).getFirstName());
                  intent.putExtra("ulname",users.get(position).getLastName());
                  holder.edbtn.getContext().startActivity(intent);
              }
          });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
       {
           TextView recid,recfname, reclname;
           ImageButton delbtn,edbtn;
           public myviewholder(@NonNull @NotNull View itemView) {
               super(itemView);

               recid=itemView.findViewById(R.id.recid);
               recfname=itemView.findViewById(R.id.recfname);
               reclname=itemView.findViewById(R.id.reclname);
               delbtn=itemView.findViewById(R.id.delbtn);
               edbtn=itemView.findViewById(R.id.edbtn);
           }
       }
}
