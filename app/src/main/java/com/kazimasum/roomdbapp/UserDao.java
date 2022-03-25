package com.kazimasum.roomdbapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertrecord(User users);

    @Query("SELECT EXISTS(SELECT * FROM User WHERE uid = :userId)")
    Boolean is_exist(int userId);


    @Query("SELECT * FROM User")
    List<User> getallusers();

    @Query("DELETE FROM User WHERE uid = :id")
    void deleteById(int id);

    @Query("UPDATE User SET first_name = :fname, last_name=:lname WHERE uid = :id")
    void updateById(int id, String fname, String lname);
}
