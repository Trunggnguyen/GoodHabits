package com.trungnvdev.goodhabits.ui.home;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trungnvdev.goodhabits.data.Perferences.AppPreferences;
import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.util.RandomQuote;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class Acount_Fragment extends Fragment {

    TextView nameuser, date, qoute, time;
    ImageView imageViewavata, ic_copy, icedit;
    View view2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acount_, container, false);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String localDate = df.format(Calendar.getInstance().getTime());


        nameuser = view.findViewById(R.id.name);
        date = view.findViewById(R.id.date);
        date.setText(localDate);
        icedit = view.findViewById(R.id.icedit);
        qoute = view.findViewById(R.id.quote);
        view2 = view.findViewById(R.id.relat);
        time = view.findViewById(R.id.goodnight);
        imageViewavata = view.findViewById(R.id.profile_image);
        ic_copy = view.findViewById(R.id.ic_copy);
        imageViewavata.setImageResource(AppPreferences.gender(getContext()));
        nameuser.setText(AppPreferences.getName(getContext()));
        view2.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));

        int min = 0;
        int max = 96;
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        qoute.setText(RandomQuote.getQuote(i1));
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 11) {
            time.setText("Chào buổi sáng");
        } else if (timeOfDay >= 11 && timeOfDay < 13) {
            time.setText("Chào buổi trưa!");
        } else if (timeOfDay >= 13 && timeOfDay < 16) {
            time.setText("Chào buổi chiều");
        } else if (timeOfDay >= 16 && timeOfDay < 24) {
            time.setText("Chào buổi tối");
        }
        icedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog( getContext());
                dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_editprofile);

                Window window = dialog.getWindow();
                if (window== null) {
                    return;
                }
                window.setLayout (WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT) ;
                window.setBackgroundDrawable (new ColorDrawable(Color. TRANSPARENT));

                WindowManager.LayoutParams windowAttributes=  window.getAttributes();
                windowAttributes.gravity  = Gravity.CENTER;
                window.setAttributes (windowAttributes);
                final int[] gioitinh2 = {AppPreferences.gender(getContext())};

                int nam = R.drawable.man;
                int nu = R.drawable.woman;
                int khac = R.drawable.user;

                dialog.show();
                TextView cacel = dialog.findViewById(R.id.edittexxtday);
                TextView textViewok = dialog.findViewById(R.id.bt_ok);
                EditText name = dialog.findViewById(R.id.inputnote);
                ImageView imageView1 = dialog.findViewById(R.id.img1);
                ImageView imageView2 = dialog.findViewById(R.id.img2);
                ImageView imageView3 = dialog.findViewById(R.id.img3);
                View view1 = dialog.findViewById(R.id.line1);
                View view2 = dialog.findViewById(R.id.line2);
                View view3 = dialog.findViewById(R.id.line3);
                name.setText(AppPreferences.getName(getContext()));

                if (gioitinh2[0] == nam){
                    imageView1.setImageResource(R.drawable.br_avata);
                }else if (gioitinh2[0] == nu){
                    imageView2.setImageResource(R.drawable.br_avata);
                }else {
                    imageView3.setImageResource(R.drawable.br_avata);
                }


                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gioitinh2[0] = nam;
                        imageView1.setImageResource(R.drawable.br_avata);
                        imageView2.setImageResource(R.drawable.br_date);
                        imageView3.setImageResource(R.drawable.br_date);

                    }
                });
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gioitinh2[0] = nu;
                        imageView2.setImageResource(R.drawable.br_avata);
                        imageView1.setImageResource(R.drawable.br_date);
                        imageView3.setImageResource(R.drawable.br_date);

                    }
                });
                view3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gioitinh2[0] = khac;
                        imageView3.setImageResource(R.drawable.br_avata);
                        imageView2.setImageResource(R.drawable.br_date);
                        imageView1.setImageResource(R.drawable.br_date);

                    }
                });
                cacel.setOnClickListener(v1 -> {
                    dialog.dismiss();
                });
                textViewok.setOnClickListener(v1 -> {
                    if (name.getText().equals("")){
                        Toast.makeText(getContext(), "Bạn chưa nhập tên !!!", Toast.LENGTH_SHORT).show();
                    }else {
                        imageViewavata.setImageResource(gioitinh2[0]);
                        nameuser.setText(name.getText().toString());
                        AppPreferences.setIntroData(getContext(), name.getText().toString(),gioitinh2[0]);
                        dialog.dismiss();
                    }

                });

            }
        });
        return view;
    }
}