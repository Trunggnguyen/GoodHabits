package com.trungnvdev.goodhabits.data.Room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.trungnvdev.goodhabits.model.Habit;
import com.trungnvdev.goodhabits.model.Note;

import java.util.List;

public class HatbitRepository {

    public Habit_Dao habit_dao;
    public LiveData<List<Habit>> getAllHabit;
    public Habit getHabit;
    public Note_Dao note_dao;
    public LiveData<List<Note>> listNote;

    public LiveData<List<Habit>> getallHabitStart;
    public LiveData<List<Habit>> getallHabitCreate;
    public LiveData<List<Habit>> getallHabitEdu;
    public LiveData<List<Habit>> getallHabitSport;
    public LiveData<List<Habit>> getallHabitHeath;
    public LiveData<List<Habit>> getallHabitOrg;

    public HatbitRepository(Application application){
        HabitDatabase database = HabitDatabase.getDatabaseInstance(application);
        habit_dao = database.HabitDao();
        note_dao= database.NoteDao();

        getAllHabit = habit_dao.getallHabit();
        getallHabitStart = habit_dao.getallHabitStart();
        getallHabitCreate = habit_dao.getallHabitCreate();
        getallHabitEdu = habit_dao.getallHabitEdu();
        getallHabitHeath = habit_dao.getallHabitHeath();
        getallHabitSport = habit_dao.getallHabitSport();
        getallHabitOrg = habit_dao.getallHabitOrg();
        listNote = note_dao.getNoteById();
    }

    public Habit getHabitbyId(int id){
        getHabit = habit_dao.getHabittById(id);
        return getHabit;
    }


    public void insertNote(Note note){
        note_dao.insertNotes(note);
    }
    public void updateNote(Note note){
        note_dao.updateNotes(note);
    }
    public void deleteNote(int id){
        note_dao.deleteNote(id);
    }

    public void insertHabit(Habit habit){
        habit_dao.insertHabits(habit);
    }

    public void deleteHabit(int id){
        habit_dao.deleteHabitById(id);
    }

    public void updatetHabit(Habit habit){
        habit_dao.updateHabts(habit);
    }




}
