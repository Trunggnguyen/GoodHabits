package com.trungnvdev.goodhabits.model;

public class Habit_Add {

    public String habitTitle;

    public String habitSubtitle;

    public String habitIcon;

    public int habitImage;

    public String habitTime;

    public int habitStart;

    public String timestart;

    public int habit_Type;

    public Habit_Add(String habitTitle, String habitSubtitle, String habitIcon, int habitImage, String habitTime, int habitStart, String timestart, int habit_Type) {
        this.habitTitle = habitTitle;
        this.habitSubtitle = habitSubtitle;
        this.habitIcon = habitIcon;
        this.habitImage = habitImage;
        this.habitTime = habitTime;
        this.habitStart = habitStart;
        this.timestart = timestart;
        this.habit_Type = habit_Type;
    }

    public int getHabit_Type() {
        return habit_Type;
    }

    public void setHabit_Type(int habit_Type) {
        this.habit_Type = habit_Type;
    }

    public String getHabitTitle() {
        return habitTitle;
    }

    public void setHabitTitle(String habitTitle) {
        this.habitTitle = habitTitle;
    }

    public String getHabitSubtitle() {
        return habitSubtitle;
    }

    public void setHabitSubtitle(String habitSubtitle) {
        this.habitSubtitle = habitSubtitle;
    }

    public String getHabitIcon() {
        return habitIcon;
    }

    public void setHabitIcon(String habitIcon) {
        this.habitIcon = habitIcon;
    }

    public int getHabitImage() {
        return habitImage;
    }

    public void setHabitImage(int habitImage) {
        this.habitImage = habitImage;
    }

    public String getHabitTime() {
        return habitTime;
    }

    public void setHabitTime(String habitTime) {
        this.habitTime = habitTime;
    }

    public int getHabitStart() {
        return habitStart;
    }

    public void setHabitStart(int habitStart) {
        this.habitStart = habitStart;
    }

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }
}
