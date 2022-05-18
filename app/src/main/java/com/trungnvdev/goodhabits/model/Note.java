package com.trungnvdev.goodhabits.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Note_Database")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public int idnote;

    @ColumnInfo(name = "id_Habit")
    public int id_Habit;

    @ColumnInfo(name = "Note")
    public String notes;

    @ColumnInfo(name = "Time_create")
    public String time;
}
