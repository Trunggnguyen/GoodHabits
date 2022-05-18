package com.trungnvdev.goodhabits.ui.habits;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;

import com.trungnvdev.goodhabits.model.Habit;
import com.trungnvdev.goodhabits.data.Room.HatbitRepository;

public class CreateHatbit_ViewModel extends AndroidViewModel {

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> sub = new ObservableField<>("");
    public ObservableField<String> icon = new ObservableField<>("");
    public ObservableField<String> time = new ObservableField<>("08:00");


    private Application mApplication;

    public Habit habit ;

    public HatbitRepository repository;

    public CreateHatbit_ViewModel(@NonNull Application application) {
        super(application);
        repository = new HatbitRepository(application);
    }

    public boolean insertHabit(){

        if (title.get().equals("") || sub.get().equals("") || icon.get().equals("") || time.get().equals("")){
            return false;
        }
        else {
            habit = new Habit();
        habit.habitTitle= title.get();
        habit.habitSubtitle= sub.get();
        habit.habitIcon= icon.get();
        habit.habitTime= time.get();
        habit.habit_Type = 0;

        habit.habitImage = 0;
        habit.habitStart = 0;
        habit.timestart= "0";
        habit.timeEnd = "0";
        habit.habit_Date=10;

        repository.insertHabit(habit);
        return true;
        }
    }

    public void insertHabit(Habit habits){
        repository.insertHabit(habits);
    }

    public void updateHabit(Habit habits){
        repository.updatetHabit(habits);
    }

}
