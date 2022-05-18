package com.trungnvdev.goodhabits.data.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.trungnvdev.goodhabits.model.Note;

import java.util.List;

@androidx.room.Dao
public interface Note_Dao {


    //notequery
    @Query("SELECT * FROM Note_Database ")
    LiveData<List<Note>> getNoteById();

    @Insert
    void  insertNotes(Note... notes);

    @Query("DELETE FROM Note_Database WHERE idnote =:id")
    void deleteNote (int id);

    @Update
    void updateNotes(Note note);
}
