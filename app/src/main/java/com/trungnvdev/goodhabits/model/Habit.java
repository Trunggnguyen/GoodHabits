package com.trungnvdev.goodhabits.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "Habits_Database")
public class Habit {

    @PrimaryKey(autoGenerate = true)
    public int habitid;

    @ColumnInfo(name = "Habit_Title")
    public String habitTitle;

    @ColumnInfo(name = "Habit_Subtitle")
    public String habitSubtitle;

    @ColumnInfo(name = "Habit_Icon")
    public String habitIcon;

    @ColumnInfo(name = "Habit_Image")
    public int habitImage;

    @ColumnInfo(name = "Habit_Time")
    public String habitTime;

    @ColumnInfo(name = "Habit_Start")
    public int habitStart;

    @ColumnInfo(name = "Habit_TimeStart")
    public String timestart;

    @ColumnInfo(name = "Habit_TimeEnd")
    public String timeEnd;

    @ColumnInfo(name = "Habit_Date")
    public int habit_Date;

    @ColumnInfo(name = "Habit_Type")
    public int habit_Type;

}
