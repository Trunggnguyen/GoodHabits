package com.trungnvdev.goodhabits.ui.habits;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.trungnvdev.goodhabits.util.receiver.AlarmReceiver;
import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.model.Habit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder>  {

    Context context;
    List<Habit> mData;
    private Habit_ItemActionListener habit_itemActionListener;

    public HabitAdapter(Context context, List<Habit> mData) {
        this.context = context;
        this.mData = mData;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habitchoiec,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  HabitAdapter.ViewHolder holder, int position) {
        Habit habit = mData.get(position);

        holder.itemView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        if (habit.habitTitle.length()>30){
            holder.textViewname.setText(habit.habitTitle.substring(0,30)+"...");
        }else {
            holder.textViewname.setText(habit.habitTitle);
        }

        if (habit.habitImage == 0){
            holder.icon_View.setText(habit.habitIcon);
            holder.icon_View1.setText(habit.habitIcon);
            holder.icon_View2.setText(habit.habitIcon);
            holder.imageView.setVisibility(View.GONE);
        }else {
            holder.imageView.setImageResource(habit.habitImage);
            holder.icon_View.setVisibility(View.GONE);
            holder.icon_View1.setVisibility(View.GONE);
            holder.icon_View2.setVisibility(View.GONE);
        }
        if (habit.habitStart ==0){
            holder.lottieAnimationView1.setVisibility(View.GONE);
            holder.day.setVisibility(View.GONE);
        }else {
            holder.day.setText(checktime(habit.timestart));
        }

        if (habit_itemActionListener != null) {
        holder.itemView.setOnClickListener(v -> {
            if (habit.habitStart==0){

                AlarmReceiver alarmReceiver;
                alarmReceiver = new AlarmReceiver();

                final Dialog dialog = new Dialog( context);
                dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_start);

                Window window = dialog.getWindow();
                if (window== null) {
                    return;
                }
                window.setLayout (WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) ;
                window.setBackgroundDrawable (new ColorDrawable(Color. TRANSPARENT));

                WindowManager.LayoutParams windowAttributes=  window.getAttributes();
                windowAttributes.gravity  = Gravity.CENTER;
                window.setAttributes (windowAttributes);

                dialog.show();
                TextView cacel = dialog.findViewById(R.id.edittexxtday);
                TextView textViewok =dialog.findViewById(R.id.bt_ok);
                cacel.setOnClickListener(v1 -> {
                    dialog.dismiss();
                    habit_itemActionListener.onItemClicked(habit.habitid, false);
                });
                textViewok.setOnClickListener(v1 -> {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
                    String localDate = df.format(Calendar.getInstance().getTime());
                    DateFormat df2 = new SimpleDateFormat(", HH:mm");
                    String localDate2 = df2.format(Calendar.getInstance().getTime());

                    alarmReceiver.setRepeatingAlarm(context, AlarmReceiver.TYPE_REPEATING,habit.habitTime, "Bạn còn thực hiện thói quen "+habit.habitTitle+" chứ ?", habit.habitid+"");

                    CreateHatbit_ViewModel createHatbit_viewModel ;
                    createHatbit_viewModel = ViewModelProviders.of((FragmentActivity) context).get(CreateHatbit_ViewModel.class);
                    Habit habit1 = new Habit();
                    habit1.habitid = habit.habitid;
                    habit1.habitTitle = habit.habitTitle;
                    habit1.habitSubtitle = habit.habitSubtitle;
                    habit1.habitImage = habit.habitImage;
                    habit1.habitTime = habit.habitTime;

                    habit1.habitIcon = habit.habitIcon;
                    habit1.habit_Date = habit.habit_Date;
                    habit1.timeEnd = habit.timeEnd;
                    habit1.timestart = localDate;
                    habit1.habit_Type = habit.habit_Type;

                    habit1.habitStart = 1;
                    createHatbit_viewModel.updateHabit(habit1);
                    habit_itemActionListener.onItemClicked(habit.habitid, true);
                    dialog.dismiss();
                });

            }else {
                habit_itemActionListener.onItemClicked(habit.habitid, true);

            }


        });
        }

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewname, day, icon_View,icon_View1,icon_View2;
        ImageView imageView;
        LottieAnimationView lottieAnimationView1;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            textViewname = itemView.findViewById(R.id.namechallen);
            day = itemView.findViewById(R.id.day);
            imageView = itemView.findViewById(R.id.imagechallen);
            icon_View = itemView.findViewById(R.id.icon_challen);
            icon_View1= itemView.findViewById(R.id.icon_challen1);
            icon_View2 = itemView.findViewById(R.id.icon_challen2);
            lottieAnimationView1= itemView.findViewById(R.id.place);
            lottieAnimationView1.playAnimation();
            lottieAnimationView1.loop(true);
        }
    }

    public void setHabit_itemActionListener(Habit_ItemActionListener listener) {
        habit_itemActionListener = listener;
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
