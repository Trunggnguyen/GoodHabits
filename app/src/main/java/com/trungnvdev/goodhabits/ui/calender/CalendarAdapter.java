package com.trungnvdev.goodhabits.ui.calender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trungnvdev.goodhabits.R;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>
{
    public static  final int BR_GONE= 0;
    public static  final int BR_VISABLE = 1;
    public static  final int BR_DAYNOW = 2;
    public static  final int BR_START = 3;
    public static  final int BR_End = 4;
    public static  final int BR_CHECK = 5;
    private  ArrayList<DateinMon> daysOfMonth;
    private  Context context;

    public CalendarAdapter(ArrayList<DateinMon> daysOfMonth, Context context) {
        this.daysOfMonth = daysOfMonth;
        this.context = context;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == BR_VISABLE) {
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_cell, parent, false);
            return new CalendarViewHolder(view);
        }else
        if (viewType == BR_DAYNOW) {
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_cell_now, parent, false);
            return new CalendarViewHolder(view);
        }else
        if (viewType == BR_START) {
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_cell_start, parent, false);
            return new CalendarViewHolder(view);
        }else
        if (viewType == BR_End) {
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_cell_choice, parent, false);
            return new CalendarViewHolder(view);
        }else
        if (viewType == BR_CHECK) {
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_cell_inchoice, parent, false);
            return new CalendarViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.calendar_cell_null, parent, false);
            return new CalendarViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull  CalendarViewHolder holder, int position) {

            holder.dayOfMonth.setText(daysOfMonth.get(position).getDaysInMon());
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }


    public class CalendarViewHolder extends RecyclerView.ViewHolder
    {

        public TextView dayOfMonth;

        public CalendarViewHolder(@NonNull  View itemView) {
            super(itemView);

                dayOfMonth = itemView.findViewById(R.id.cellDayText);


        }

    }
    @Override
    public int getItemViewType(int position) {

        if (daysOfMonth.get(position).getDaysInMon().equals("")){
            return BR_GONE;
        }
        else {
            if (daysOfMonth.get(position).isDatenow()) {
                return BR_DAYNOW;
            } else {
                if (daysOfMonth.get(position).isStart()) {
                    return BR_START;
                } else {
                    if (daysOfMonth.get(position).isEnd()) {
                        return BR_End;
                    } else {
                        if (daysOfMonth.get(position).isCheck()) {
                            return BR_CHECK;
                        } else {
                            return BR_VISABLE;
                        }
                    }
                }
            }
        }
    }

}


