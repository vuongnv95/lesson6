package com.example.demorealm.room;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RenameTable;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@SuppressLint("RestrictedApi")
@Database(entities = {UserEntity.class,AddressEntity.class,PetEntity.class}, version = UserDatabase.DATABASE_VERSION,
        autoMigrations = {
                @AutoMigration (
                        from = 1,
                        to = 2,
                        spec = UserDatabase.MyAutoMigration.class
                )
        }
)
public abstract class UserDatabase extends RoomDatabase {


        @RenameTable(fromTableName = "users", toTableName = "users_migration_v2")
        static class MyAutoMigration implements AutoMigrationSpec { }

    private static UserDatabase sUserDatabase;

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Room-database";

    public abstract UserDao userDAO();
    public abstract AddressDao addressDAO();
    public abstract PetDao petDAO();

    public static UserDatabase getInstance(Context context) {
        if (sUserDatabase == null) {
            sUserDatabase = Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return sUserDatabase;
    }

//    public static UserDatabase getInstance(Context context) {
//        if (sUserDatabase == null) {
//            sUserDatabase = Room.databaseBuilder(context, UserDatabase.class, DATABASE_NAME)
//                    .addMigrations(MIGRATION_1_2)
//                    .build();
//        }
//        return sUserDatabase;
//    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users "
                    + " ADD COLUMN date_of_birth TEXT");
        }
    };
}
