package com.trungnvdev.goodhabits.ui.habits;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.databinding.FragmentChoiceHabitBinding;

public class ChoiceHabit_Fragment extends Fragment {
    private FragmentChoiceHabitBinding mBinding;
    HabitVewModel habitVewModel;
    HabitAdapter habitAdapter;
    public static final int ARG_HABIT_ID = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choice_habit_, container, false);

        habitVewModel = ViewModelProviders.of(this).get(HabitVewModel.class);
        mBinding = FragmentChoiceHabitBinding.bind(view);
        mBinding.setViewmodel(habitVewModel);
        mBinding.setLifecycleOwner(this);

        Toolbar toolbar = mBinding.toolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(getView()).navigateUp();

        });
        setupRecyker();
        return view;
    }

    private void setupRecyker() {
        habitVewModel = ViewModelProviders.of(this).get(HabitVewModel.class);

        habitVewModel.getallHabitCreate.observe(getViewLifecycleOwner(), habits -> {
           // Log.d("TAG", "setupRecyker: " + habits.size());
            if (habits.size() > 0) {
                mBinding.createnew.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mBinding.recycllerview1.setLayoutManager(linearLayoutManager);
                habitAdapter = new HabitAdapter(getContext(), habits);
                mBinding.recycllerview1.setAdapter(habitAdapter);
                habitAdapter.setHabit_itemActionListener(new Habit_ItemActionListener() {
                    @Override
                    public void onItemClicked(int habitId, boolean check) {
                        if (check){
                            Bundle bundle = new Bundle();
                            //Log.d("TAG", "onItemClicked: "+ habitId);
                            bundle.putInt(Habit_Fragment.HABIT_ID, habitId);

                            Navigation.findNavController(getView()).navigate(R.id.action_openhabit2, bundle);
                        }
                        }

                });
            }else {
                mBinding.createnew.setVisibility(View.GONE);
            }
        });
        habitVewModel.getallHabitEdu.observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() > 0) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mBinding.recycllerview2.setLayoutManager(linearLayoutManager);
                habitAdapter = new HabitAdapter(getContext(), habits);
                mBinding.recycllerview2.setAdapter(habitAdapter);

                habitAdapter.setHabit_itemActionListener(new Habit_ItemActionListener() {
                    @Override
                    public void onItemClicked(int habitId, boolean check) {
                        if (check){
                            Bundle bundle = new Bundle();
                           // Log.d("TAG", "onItemClicked: "+ habitId);
                            bundle.putInt(Habit_Fragment.HABIT_ID, habitId);

                            Navigation.findNavController(getView()).navigate(R.id.action_openhabit2, bundle);
                        }
                    }

                });

            }
        });
        habitVewModel.getallHabitHeath.observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() > 0) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mBinding.recycllerview3.setLayoutManager(linearLayoutManager);
                habitAdapter = new HabitAdapter(getContext(), habits);
                mBinding.recycllerview3.setAdapter(habitAdapter);
                habitAdapter.setHabit_itemActionListener(new Habit_ItemActionListener() {
                    @Override
                    public void onItemClicked(int habitId, boolean check) {
                        if (check){
                            Bundle bundle = new Bundle();
                           // Log.d("TAG", "onItemClicked: "+ habitId);
                            bundle.putInt(Habit_Fragment.HABIT_ID, habitId);
                            Navigation.findNavController(getView()).navigate(R.id.action_openhabit2, bundle);
                        }
                    }

                });
            }
        });
        habitVewModel.getallHabitSport.observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() > 0) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mBinding.recycllerview4.setLayoutManager(linearLayoutManager);
                habitAdapter = new HabitAdapter(getContext(), habits);
                mBinding.recycllerview4.setAdapter(habitAdapter);
                habitAdapter.setHabit_itemActionListener(new Habit_ItemActionListener() {
                    @Override
                    public void onItemClicked(int habitId, boolean check) {
                        if (check){
                            Bundle bundle = new Bundle();
                           // Log.d("TAG", "onItemClicked: "+ habitId);
                            bundle.putInt(Habit_Fragment.HABIT_ID, habitId);
                            Navigation.findNavController(getView()).navigate(R.id.action_openhabit2, bundle);
                        }
                    }

                });
            }
        });
        habitVewModel.getallHabitOrg.observe(getViewLifecycleOwner(), habits -> {
            if (habits.size() > 0) {
                //Log.d("TAG", "setupRecyker: " + habits.size());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                mBinding.recycllerview5.setLayoutManager(linearLayoutManager);
                habitAdapter = new HabitAdapter(getContext(), habits);
                mBinding.recycllerview5.setAdapter(habitAdapter);
                habitAdapter.setHabit_itemActionListener(new Habit_ItemActionListener() {
                    @Override
                    public void onItemClicked(int habitId, boolean check) {
                        if (check){
                            Bundle bundle = new Bundle();
                           // Log.d("TAG", "onItemClicked: "+ habitId);
                            bundle.putInt(Habit_Fragment.HABIT_ID, habitId);
                            Navigation.findNavController(getView()).navigate(R.id.action_openhabit2, bundle);
                        }
                    }

                });
            }
        });

    }
}