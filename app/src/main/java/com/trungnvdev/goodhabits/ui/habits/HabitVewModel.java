package com.trungnvdev.goodhabits.ui.habits;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.model.Habit;
import com.trungnvdev.goodhabits.data.Room.HatbitRepository;
import com.trungnvdev.goodhabits.model.Note;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class HabitVewModel extends AndroidViewModel {

    public Habit habit ;
    public Habit habit2 ;
    public Habit habit3 ;

    public HatbitRepository repository;
    public LiveData<List<Habit>> getAllHabit;
    public LiveData<List<Habit>> getallHabitStart;
    public LiveData<List<Habit>> getallHabitCreate;
    public LiveData<List<Habit>> getallHabitEdu;
    public LiveData<List<Habit>> getallHabitSport;
    public LiveData<List<Habit>> getallHabitHeath;
    public LiveData<List<Habit>> getallHabitOrg;

    public LiveData<List<Note>> getNotebyID;

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> icon = new ObservableField<>("");
    public ObservableField<String> sub = new ObservableField<>("");
    public ObservableField<String> date = new ObservableField<>("");
    public ObservableField<String> day = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("(08:00)");
    public ObservableField<Integer> imageRes = new ObservableField<Integer>(R.drawable.br_null);


    public HabitVewModel(@NonNull  Application application) {
        super(application);
        repository = new HatbitRepository(application);
        getAllHabit = repository.getAllHabit;
        getallHabitStart = repository.getallHabitStart;
        getallHabitCreate =repository.getallHabitCreate;
        getallHabitEdu = repository.getallHabitEdu;
        getallHabitHeath = repository.getallHabitHeath;
        getallHabitSport = repository.getallHabitSport;
        getallHabitOrg =repository.getallHabitOrg;
        getNotebyID= repository.listNote;
    }

    public void setData(  int id){
        String hahabitStart;
        habit = repository.getHabitbyId(id);
        title.set(habit.habitTitle);
        sub.set(habit.habitSubtitle);
        date.set(habit.timestart);
        hahabitStart = habit.timestart;
        time.set("("+ habit.habitTime+ ")");
        day.set(checktime(hahabitStart));
        Log.d("TAG", "setData: "+ habit.habitTitle +habit. habitImage+ habit.habitIcon);
        if (habit.habitImage==0){
            icon.set(habit.habitIcon);

        }else {
            imageRes.set(habit.habitImage);
        }
    }
    public Habit getHabitbyID( int id){
        habit2 = repository.getHabitbyId(id);
       return  habit2;

    }

    public boolean checkDelete( int id){
        habit3 = repository.getHabitbyId(id);
        if (habit3.habitImage!=0){
            return false;
        }else return true;

    }


    public void insertNote(Note note){
        repository.insertNote(note);
    }

    public void updateNote(Note note){
        repository.updateNote(note);
    }

    public void stopHabit(int habitID){

        Habit habit = repository.getHabitbyId(habitID);
        Habit habit1 = new Habit();

        habit1.habitid = habit.habitid;
        habit1.habitTitle = habit.habitTitle;
        habit1.habitSubtitle = habit.habitSubtitle;
        habit1.habitImage = habit.habitImage;

        habit1.habitIcon = habit.habitIcon;
        habit1.habit_Date = habit.habit_Date;
        habit1.timeEnd = habit.timeEnd;
        habit1.timestart = "0";
        habit1.habitTime = habit.habitTime;
        habit1.habit_Type = habit.habit_Type;
        habit1.habitStart = 0;

        repository.updatetHabit(habit1);
    }
    public void updateHabit(int habitID, String sub){

        Habit habit = repository.getHabitbyId(habitID);
        Habit habit1 = new Habit();

        habit1.habitid = habit.habitid;
        habit1.habitTitle = habit.habitTitle;
        habit1.habitSubtitle = sub;
        habit1.habitImage = habit.habitImage;

        habit1.habitIcon = habit.habitIcon;
        habit1.habit_Date = habit.habit_Date;
        habit1.timeEnd = habit.timeEnd;
        habit1.timestart = habit.timestart;

        habit1.habitTime = habit.habitTime;

        habit1.habit_Type = habit.habit_Type;
        habit1.habitStart =habit.habitStart;
        repository.updatetHabit(habit1);
    }

    public void updateHabitime(int habitID, String time){

        Habit habit = repository.getHabitbyId(habitID);
        Habit habit1 = new Habit();

        habit1.habitid = habit.habitid;
        habit1.habitTitle = habit.habitTitle;
        habit1.habitSubtitle = habit.habitSubtitle;
        habit1.habitImage = habit.habitImage;

        habit1.habitIcon = habit.habitIcon;
        habit1.habit_Date = habit.habit_Date;
        habit1.timeEnd = habit.timeEnd;
        habit1.timestart = habit.timestart;

        habit1.habitTime = time;

        habit1.habit_Type = habit.habit_Type;
        habit1.habitStart =habit.habitStart;
        repository.updatetHabit(habit1);
    }



    public void deleteNote(int id){
        repository.deleteNote(id);
    }
    public void deleteHabit(int id){
        repository.deleteHabit(id);
    }

    private String checktime( String hahabitStart) {
        String  daystart, monstart, yearstart;

        daystart = hahabitStart.substring(8, 10);
        yearstart = hahabitStart.substring(0, 4);
        monstart = hahabitStart.substring(5, 7);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String inputTimenow = df.format(Calendar.getInstance().getTime());
        String inputTimestart = yearstart+"-" +monstart+"-" + daystart;

        LocalDate dateStart = LocalDate.parse(inputTimestart, dtf);
        LocalDate dateNow = LocalDate.parse(inputTimenow, dtf);
        long daysBetweenowstart = Duration.between(dateStart.atStartOfDay(), dateNow.atStartOfDay()).toDays();

        return  daysBetweenowstart+" ngày";
    }
}
