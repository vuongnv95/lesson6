package com.example.demorealm.room;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AddressDao {
    @Query("SELECT * FROM address WHERE province = :province")
    public List<AddressEntity> getAddress(String province);
}
