package com.trungnvdev.goodhabits.data.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.trungnvdev.goodhabits.model.Habit;

import java.util.List;

@androidx.room.Dao
public interface Habit_Dao {

    //habitsquery
    @Query("SELECT * FROM Habits_Database")
    LiveData<List<Habit>> getallHabit();

    @Query("SELECT * FROM Habits_Database WHERE Habit_Start = 1")
    LiveData<List<Habit>> getallHabitStart();

    @Query("SELECT * FROM Habits_Database WHERE Habit_Type = 0")
    LiveData<List<Habit>> getallHabitCreate();

    @Query("SELECT * FROM Habits_Database WHERE Habit_Type = 1")
    LiveData<List<Habit>> getallHabitEdu();

    @Query("SELECT * FROM Habits_Database WHERE Habit_Type = 2")
    LiveData<List<Habit>> getallHabitHeath();

    @Query("SELECT * FROM Habits_Database WHERE Habit_Type = 3")
    LiveData<List<Habit>> getallHabitSport();

    @Query("SELECT * FROM Habits_Database WHERE Habit_Type = 4")
    LiveData<List<Habit>> getallHabitOrg();


    @Query("SELECT * FROM Habits_Database WHERE habitid = :id")
    Habit getHabittById(int id);

    @Insert
    void  insertHabits(Habit... habits);

    @Query("DELETE FROM Habits_Database WHERE habitid =:id")
    void deleteHabitById (int id);

    @Update
    void updateHabts(Habit habit);

}
