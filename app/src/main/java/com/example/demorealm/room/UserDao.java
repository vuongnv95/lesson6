package com.example.demorealm.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    public List<UserEntity> getAllUsers(int userId);

    @Query("SELECT * FROM users WHERE first_name LIKE :userName OR last_name LIKE :userName")
    List<UserEntity> getUserByName(String userName);

    @Query("SELECT * FROM users")
    List<UserEntity> getALlUser();

    @Insert
    void insertUser(UserEntity... users);

    @Delete
    void deleteUser(UserEntity user);

    @Query("DELETE FROM users")
    void deleteAllUser();

    @Update
    void updateUser(UserEntity... users);

}
