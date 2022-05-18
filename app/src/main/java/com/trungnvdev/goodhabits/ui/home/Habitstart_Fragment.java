package com.trungnvdev.goodhabits.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.trungnvdev.goodhabits.ui.habits.HabitVewModel;
import com.trungnvdev.goodhabits.ui.habits.Habit_Fragment;
import com.trungnvdev.goodhabits.ui.habits.Habit_ItemActionListener;

import com.trungnvdev.goodhabits.R;

public class Habitstart_Fragment extends Fragment {
    RecyclerView recyclerView;

    HabitVewModel habitVewModel;
    HabitHome_Adapter habitAdapter;
    LinearLayout linearLayout ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_habitstart_, container, false);
        recyclerView = view.findViewById(R.id.recycllerstart);
        linearLayout = view.findViewById(R.id.linearstart);
        linearLayout.setVisibility(View.GONE);

        habitVewModel = ViewModelProviders.of(this).get(HabitVewModel.class);

        habitVewModel.getallHabitStart.observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() > 0) {
                recyclerView.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);

                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                habitAdapter = new HabitHome_Adapter(getContext(), habits);
                recyclerView.setAdapter(habitAdapter);
                habitAdapter.setHabit_itemActionListener(new Habit_ItemActionListener() {
                    @Override
                    public void onItemClicked(int habitId, boolean check) {
                        if (check){
                            Bundle bundle = new Bundle();
                            bundle.putInt(Habit_Fragment.HABIT_ID, habitId);
                            Navigation.findNavController(getView()).navigate(R.id.action_openhabit, bundle);
                        }
                    }

                });
            }else {
                recyclerView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_scale_animation));
            }
        });

        return  view;
    }
}