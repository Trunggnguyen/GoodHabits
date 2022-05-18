package com.trungnvdev.goodhabits.ui.habits;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TimePicker;
import android.widget.Toast;

import com.trungnvdev.goodhabits.R;

import com.trungnvdev.goodhabits.databinding.FragmentCreateHabtsBinding;
import com.vanniktech.emoji.EmojiPopup;

import java.util.Calendar;

public class CreateHabts_Fragment extends Fragment {

    private FragmentCreateHabtsBinding mBinding;
    private CreateHatbit_ViewModel mViewModel;
    private int hourRepeating, minuteRepeating;
    private  EmojiPopup emojiPopup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();
        hourRepeating = calendar.get(Calendar.HOUR_OF_DAY);
        minuteRepeating = calendar.get(Calendar.MINUTE);

        View view = inflater.inflate(R.layout.fragment_create_habts_, container, false);

        mViewModel = ViewModelProviders.of(this).get(CreateHatbit_ViewModel.class);
        mBinding = FragmentCreateHabtsBinding.bind(view);
        mBinding.setViewmodel(mViewModel);
        mBinding.setLifecycleOwner(this);


        Toolbar toolbar = mBinding.toolbar;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v ->  {
                Navigation.findNavController(getView()).navigateUp();

        });
        loadEmoj();

        CreateActionListener actionListener = getCreatActionListener();
        mBinding.setListener(actionListener);
         emojiPopup = EmojiPopup.Builder.fromRootView(mBinding.rootview2).build(mBinding.editemoj);

        return view;

    }

    private void loadEmoj() {
        mBinding.fabAdd.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.textviewEdit.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.imageEdit.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.editemoj.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.textnhan.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.subchallen.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.titlechallen.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.timechallen.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
        mBinding.texttimechallen.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));



    }

    private CreateActionListener getCreatActionListener() {
        return new CreateActionListener(){

            @Override
            public void onClickTitle() {

            }

            @Override
            public void onClickSub() {
                onClicksView();
            }


            @Override
            public void onClickEmoji() {
                getemoji();
            }

            @Override
            public void onClickTime() {
                onClicksetuptme();

            }

            @Override
            public void onFabClicked() {
                setupFab();
            }
        };
    }

    private void setupFab() {
        if (mViewModel.insertHabit()) {
            Navigation.findNavController(getView()).navigateUp();
            Toast.makeText(getContext(), "Tạo thành công !!!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Thứ gì đó bi bỏ qua !!!", Toast.LENGTH_SHORT).show();
        }
    }
    private void onClicksView() {
        if (emojiPopup.isShowing()){
            emojiPopup.dismiss();
        }

        mBinding.viewclick.setVisibility(View.GONE);

    }
    private void onClicksetuptme() {
        if (emojiPopup.isShowing()){
            emojiPopup.dismiss();
        }
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
                mViewModel.time.set(hourday+":"+minuteday);
                hourRepeating= minute1;
                minuteRepeating = hourOfDay;
            }
        }, hourRepeating, minuteRepeating, true);
        timePickerDialog.show();


    }

    private void getemoji() {

                if (!emojiPopup.isShowing()){
                    emojiPopup.toggle();
                }
                mBinding.viewclick.setVisibility(View.VISIBLE);
        mBinding.editemoj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mViewModel.icon.set(s.subSequence(s.length()-count, s.length()).toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    mBinding.imageEdit.setVisibility(View.GONE);
                }

            }
        });

    }


}