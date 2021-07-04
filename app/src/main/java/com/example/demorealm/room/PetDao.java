package com.example.demorealm.room;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PetDao {
    @Query("SELECT * FROM users WHERE petId = :petId")
    public List<PetEntity> getAllPets(int petId);
}
