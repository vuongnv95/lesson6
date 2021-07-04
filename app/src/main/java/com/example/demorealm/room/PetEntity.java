package com.example.demorealm.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "pets",foreignKeys = @ForeignKey(entity = UserEntity.class,
        parentColumns = "id",
        childColumns = "user_id"))
public class PetEntity {
    @PrimaryKey
    public int petId;

    public String name;

    @ColumnInfo(name = "user_id")
    public int userId;

}
