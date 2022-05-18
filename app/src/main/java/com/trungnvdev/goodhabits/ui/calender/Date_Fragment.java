package com.trungnvdev.goodhabits.ui.calender;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.model.Habit;
import com.trungnvdev.goodhabits.databinding.FragmentDateBinding;
import com.trungnvdev.goodhabits.ui.habits.HabitVewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class Date_Fragment extends Fragment {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private ImageView imageViewleft, imageViewright;
    private HabitVewModel habitVewModell;
    private Habit habit;
    private String hahabitStart, daystart, monstart, yearstart;
//    private String challengeEnd, dayEnd, monEnd, yearEnd;
    private FragmentDateBinding mbinding;
    int id ;
    public Date_Fragment(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_date_, container, false);
        selectedDate = LocalDate.now();
        habitVewModell = ViewModelProviders.of(this).get(HabitVewModel.class);
        mbinding = FragmentDateBinding.bind(view);
        mbinding.setViewmodel(habitVewModell);
        mbinding.setLifecycleOwner(this);
        habit = habitVewModell.getHabitbyID(id);
        hahabitStart = habit.timestart;
//        challengeEnd = challenge.timeend;

        daystart = hahabitStart.substring(8, 10);
        yearstart = hahabitStart.substring(0, 4);
        monstart = hahabitStart.substring(5, 7);

//        dayEnd = challengeEnd.substring(8, 10);
//        yearEnd = challengeEnd.substring(0, 4);
//        monEnd = challengeEnd.substring(5, 7);

        calendarRecyclerView = mbinding.calendarRecyclerView;
        monthYearText = mbinding.monthYearTV;
        imageViewleft = mbinding.imageleft;
        imageViewright = mbinding.imageright;
        imageViewleft.setOnClickListener(v -> {
            previousMonthAction(view);
        });
        imageViewright.setOnClickListener(v -> {
            nextMonthAction(view);
        });


        setMonthView();

        return  view;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView()
    {

        String mothyear = monthYearFromDate(selectedDate);
        String firstLetter = mothyear.substring(0, 1);
        String mothyear2 = firstLetter.toUpperCase() +  mothyear.substring(1, mothyear.length());
        monthYearText.setText(mothyear2);
        ArrayList<DateinMon> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }
    private ArrayList<DateinMon> daysInMonthArray(LocalDate date)
    {
        ArrayList<DateinMon> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        MonthDay monthDay = MonthDay.from(date);
        LocalDate localDate2 = LocalDate.now();

        int daysInMonth = yearMonth.lengthOfMonth();
        String month = date.getMonthValue() +"";
        String monnow = localDate2.getMonthValue() +"";

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <= 42; i++)
        {
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add(new DateinMon("", false, false, false, false) );
            }else{
                if(localDate2.getDayOfMonth() == i - dayOfWeek && YearMonth.from(date).toString().equals(YearMonth.from(localDate2).toString()) ){

                    if(Integer.parseInt(daystart)== i - dayOfWeek && YearMonth.from(date).toString().equals(yearstart+"-"+monstart) ){
                        daysInMonthArray.add(new DateinMon(String.valueOf(i - dayOfWeek), false, false, false, true));
                    }else daysInMonthArray.add(new DateinMon(String.valueOf(i - dayOfWeek), false, true, false, false));
                }
//                else
//                if(Integer.parseInt(dayEnd)== i - dayOfWeek && YearMonth.from(date).toString().equals(yearEnd+"-"+monEnd) ){
//                    daysInMonthArray.add(new DateinMon(String.valueOf(i - dayOfWeek), false, false, false, true));
//                }
                else
                if(Integer.parseInt(daystart)== i - dayOfWeek && YearMonth.from(date).toString().equals(yearstart+"-"+monstart) ){
                    daysInMonthArray.add(new DateinMon(String.valueOf(i - dayOfWeek), true, false, true, false));
                }
                else
                if(checktime( i - dayOfWeek ,date )){
                    daysInMonthArray.add(new DateinMon(String.valueOf(i - dayOfWeek), true, false, false, false));
                }
                else {
                    daysInMonthArray.add(new DateinMon(String.valueOf(i - dayOfWeek), false, false, false, false));
                }
            }
        }
        int count = 0;
        for(int i = 0; i <= daysInMonthArray.size(); i++){
            if (daysInMonthArray.get(i).getDaysInMon().equals("")){
                count++;
            }else {
                break;
            }
        }
        if (count==7){
            for (int i = 0; i < 7; i++) {
                daysInMonthArray.remove(0);
            }
        }
        return  daysInMonthArray;
    }
    private boolean checktime( int b, LocalDate date) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String inputTimenow = df.format(Calendar.getInstance().getTime());
        String inputTimestart = yearstart+"-" +monstart+"-" + daystart;
        String inputTimerun;
        if (b<10){
            inputTimerun = YearMonth.from(date) +"-0"+b;
        }else {
            inputTimerun = YearMonth.from(date) +"-"+b;
        }


        LocalDate dateStart = LocalDate.parse(inputTimestart, dtf);
        LocalDate dateNow = LocalDate.parse(inputTimenow, dtf);
        LocalDate dateRun = LocalDate.parse(inputTimerun, dtf);

        long daysBetweenrunstart = Duration.between(dateStart.atStartOfDay(), dateRun.atStartOfDay()).toDays();
        long daysBetweenowstart = Duration.between(dateStart.atStartOfDay(), dateNow.atStartOfDay()).toDays();

        //Log.d("TAG",  daysBetweenrunstart+"");

        if (daysBetweenrunstart > 0 && daysBetweenowstart > daysBetweenrunstart){
            return true;
        }else {

            return false;
        }

    }
    private String monthYearFromDate(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }


    public void previousMonthAction(View view)
    {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }


    public void nextMonthAction(View view)
    {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }
}