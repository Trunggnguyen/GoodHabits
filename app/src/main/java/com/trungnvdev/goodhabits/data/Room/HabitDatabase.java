package com.trungnvdev.goodhabits.data.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.trungnvdev.goodhabits.model.Habit;
import com.trungnvdev.goodhabits.model.Note;


@Database(entities =  {Habit.class, Note.class}, version = 1)
public abstract class HabitDatabase extends RoomDatabase {
    public abstract Habit_Dao HabitDao();
    public abstract Note_Dao NoteDao();

    public static HabitDatabase INSTANCE;

    public static HabitDatabase getDatabaseInstance(Context context) {
        if(INSTANCE == null ){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                    HabitDatabase.class, "Habit_Database.db").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
