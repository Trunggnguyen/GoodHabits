package com.trungnvdev.goodhabits.ui.habits;

import android.animation.Animator;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.trungnvdev.goodhabits.util.receiver.AlarmReceiver;
import com.trungnvdev.goodhabits.data.Perferences.AppPreferences;
import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.ui.calender.Date_Fragment;
import com.trungnvdev.goodhabits.model.Habit;

import com.trungnvdev.goodhabits.model.Note;
import com.trungnvdev.goodhabits.databinding.FragmentHabitBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Habit_Fragment extends Fragment  {
    public static final String HABIT_ID ="HABIT_ID" ;
    private int habitID;
    private FragmentHabitBinding mBinding;
    HabitVewModel habitVewModel;
    HabitAdapter habitAdapter;
    Habit habit;
    private FragmentActivity myContext;
    FloatingActionButton fabOpen, fabclose, fab1, fab2, fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout, fabBGmenu;
    boolean isFABOpen = false;
    TextView textViewfab1, textViewfab2, textViewfab3,icon_View,icon_View1, icon_View2 , day, extViewname;
    Dialog dialog;
    private  List<Note> noteList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view =  inflater.inflate(R.layout.fragment_habit_, container, false);
        habitVewModel = ViewModelProviders.of(this).get(HabitVewModel.class);
        mBinding = FragmentHabitBinding.bind(view);
        mBinding.setViewmodel(habitVewModel);
        mBinding.setLifecycleOwner(this);

        Toolbar toolbar = mBinding.toolbarmain;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v ->  {
            Navigation.findNavController(getView()).navigateUp();
        });
        habitID =getArguments().getInt(HABIT_ID);
        habitVewModel.setData(habitID);
        mBinding.place.playAnimation();
        mBinding.place.loop(true);

        setupdate();
        setupMenu();
        getnote();
        editSub();


       return  view;
    }

    private void editSub() {
        mBinding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog( getContext());
                dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_addnote);

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
                TextView textView = dialog.findViewById(R.id.titel);
                textView.setText("Chỉnh sửa mô tả của bạn !");
                TextView textViewok =dialog.findViewById(R.id.bt_ok);
                EditText editText = dialog.findViewById(R.id.inputnote);
                editText.setText(mBinding.subhabit.getText());
                cacel.setOnClickListener(v1 -> {
                    dialog.dismiss();
                });

                textViewok.setOnClickListener(v1 -> {
                    if (editText.getText().toString().equals("")){
                        Toast.makeText(myContext, "Ban chưa nhập nội dung", Toast.LENGTH_SHORT).show();
                    }else {
                        habitVewModel.updateHabit(habitID, editText.getText().toString());
                        mBinding.subhabit.setText(editText.getText().toString());
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    private void getnote() {
        RecyclerView recyclerView;
         recyclerView= mBinding.recyclernote;
        habitVewModel.getNotebyID.observe(getViewLifecycleOwner(), notes ->{
            List<Note> noteList = new ArrayList<>();
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).id_Habit== habitID){
                    noteList.add(notes.get(i));
                }
            }
            if (noteList.size() > 0) {
                mBinding.gichu.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                Note_Adapter note_adapter = new Note_Adapter( noteList, getContext());
                recyclerView.setAdapter(note_adapter);
            }else {
                recyclerView.setVisibility(View.GONE);
                mBinding.gichu.setVisibility(View.GONE);
            }
        });
    }

    private void setupdate() {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayoutchallenge, new Date_Fragment(habitID));
        transaction.commit();

    }

    private void setupMenu(){
        textViewfab1 = mBinding.textViewfab1;
        textViewfab2 = mBinding.textViewfab2;
        textViewfab3 = mBinding.textViewfab3;

        textViewfab1.setText(R.string.namefab1);
        textViewfab2.setText(R.string.namefab2);
        textViewfab3.setText(R.string.namefab3);
        fabBGmenu = mBinding.menushow;


        textViewfab1.setVisibility(View.GONE);
        textViewfab2.setVisibility(View.GONE);
        textViewfab3.setVisibility(View.GONE);
        fabBGmenu.setVisibility(View.GONE);

        fabLayout1 =  mBinding.fabLayout1;
        fabLayout2 =  mBinding.fabLayout2;
        fabLayout3 = mBinding.fabLayout3;
        fabOpen =  mBinding.fabopen;
        fabclose =  mBinding.fabclose;
        fab1 =  mBinding.fab1;
        fab2 =  mBinding.fab2;
        fab3 =  mBinding.fab3;
        fabBGLayout = mBinding.fabBGLayout;

        fabOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFABMenu();
            }
        });
        fabclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
            }
        });

        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFABMenu();
                final Dialog dialog = new Dialog( getContext());
                dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_stop);
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
                });
                textViewok.setOnClickListener(v1 -> {
                    habitVewModel.stopHabit(habitID);
                    AlarmReceiver alarmReceiver = new AlarmReceiver();
                    alarmReceiver.cancelAlarm(getContext(),AlarmReceiver.TYPE_REPEATING,habitID+"");
                    dialog.dismiss();
                    Navigation.findNavController(getView()).navigateUp();
                });

            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFABMenu();
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute1) {
                        String hourday;
                        String minuteday;
                        if (hourOfDay<10) {
                            hourday ="0"+ hourOfDay;
                        }else {
                            hourday = hourOfDay + "";
                        }
                        if (minute1<10) {
                            minuteday ="0"+ minute1;
                        }else {
                            minuteday = minute1 + "";
                        }

                        AppPreferences.setTime(getContext(),hourday+":"+minuteday );
                        habitVewModel.updateHabitime(habitID, hourday+":"+minuteday);
                        AlarmReceiver alarmReceiver = new AlarmReceiver();
                        alarmReceiver.cancelAlarm(getContext(),AlarmReceiver.TYPE_REPEATING,habitID+"");
                        alarmReceiver.setRepeatingAlarm(getContext(), AlarmReceiver.TYPE_REPEATING,hourday+":"+minuteday, "Bạn còn thực hiện thói quen "+mBinding.namehabit.getText()+" chứ ?", habitID+"");
                    }
                }, 8, 0, true);
                timePickerDialog.show();

            }
        });
        fab3.setOnClickListener(v -> {
            closeFABMenu();
            final Dialog dialog = new Dialog( getContext());
            dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_addnote);

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
            EditText editText = dialog.findViewById(R.id.inputnote);
            cacel.setOnClickListener(v1 -> {
                dialog.dismiss();
            });

            textViewok.setOnClickListener(v1 -> {
                if (editText.getText().toString().equals("")){
                    Toast.makeText(myContext, "Ban chưa nhập nội dung", Toast.LENGTH_SHORT).show();
                }else {
                Note note = new Note();
                note.id_Habit= habitID;
                note.notes= editText.getText().toString();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
                String localDate = df.format(Calendar.getInstance().getTime());
                note.time = localDate;
                habitVewModel.insertNote(note);
                    dialog.dismiss();
                }
            });

        });

    }


    private void showFABMenu() {
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);
        fabBGmenu.setVisibility(View.VISIBLE);
        fabOpen.animate().rotationBy(180);
        fabBGmenu.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_menu_animation));
        fabLayout1.animate().translationX(-getResources().getDimension(R.dimen.standard_100));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2.animate().translationX(-getResources().getDimension(R.dimen.standard_55));
        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_100))
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        fabOpen.setVisibility(View.GONE);
                        textViewfab1.setVisibility(View.VISIBLE);
                        textViewfab2.setVisibility(View.VISIBLE);
                        textViewfab3.setVisibility(View.VISIBLE);

                    }
                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

    }

    private void closeFABMenu() {
        textViewfab1.setVisibility(View.GONE);
        textViewfab2.setVisibility(View.GONE);
        textViewfab3.setVisibility(View.GONE);
        fabBGmenu.setVisibility(View.GONE);
        fabBGLayout.setVisibility(View.GONE);
        isFABOpen = false;
        fabOpen.setVisibility(View.VISIBLE);
        fabOpen.animate().rotation(0);
        fabBGmenu.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_menu_animation_back));
        fabLayout1.animate().translationX(0);
        fabLayout2.animate().translationY(0);
        fabLayout2.animate().translationX(0);
        fabLayout3.animate().translationY(0);
        fabLayout3.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (!isFABOpen) {
                    fabLayout1.setVisibility(View.GONE);
                    fabLayout2.setVisibility(View.GONE);
                    fabLayout3.setVisibility(View.GONE);

                }
            }
            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (habitVewModel.checkDelete(habitID)){
            inflater.inflate(R.menu.delete_habit, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            closeFABMenu();
            final Dialog dialog = new Dialog( getContext());
            dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_delete);
            Window window = dialog.getWindow();
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
            });

            textViewok.setOnClickListener(v1 -> {
                AlarmReceiver alarmReceiver = new AlarmReceiver();
                alarmReceiver.cancelAlarm(getContext(),AlarmReceiver.TYPE_REPEATING,habitID+"");
                habitVewModel.deleteHabit(habitID);
                dialog.dismiss();
                Navigation.findNavController(getView()).navigateUp();
            });

        }
        return true;
    }

}