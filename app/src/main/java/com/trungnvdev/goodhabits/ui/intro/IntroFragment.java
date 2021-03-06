package com.trungnvdev.goodhabits.ui.intro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trungnvdev.goodhabits.util.receiver.AlarmReceiver;
import com.trungnvdev.goodhabits.data.Perferences.AppPreferences;
import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.util.anim.CustomViewPager;
import com.trungnvdev.goodhabits.model.Habit;
import com.trungnvdev.goodhabits.model.Habit_Add;
import com.trungnvdev.goodhabits.ui.habits.CreateHatbit_ViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroFragment extends Fragment {

    private CustomViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter ;

    TabLayout tabIndicator;
    Button btnNext;
    int position = 0 ;
    Button btnGetStarted;
    Animation btnAnim ;
    TextView tvSkip;
    ImageView imageViewnam, imageViewnu, imageViewnamnu;
    EditText editTextname;
    View linearLayout1 ;
    int giioitinh;
    Habit habit;
    List<Habit_Add> mData;
    CreateHatbit_ViewModel createHatbit_viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_intro, container, false);

        imageViewnam = view.findViewById(R.id.imageavatanam);
        imageViewnu = view.findViewById(R.id.imageavatanu);
        imageViewnamnu = view.findViewById(R.id.imageavatanamnu);
        editTextname = view.findViewById(R.id.nameinput);
        btnNext = view.findViewById(R.id.btn_next);
        btnGetStarted = view.findViewById(R.id.btn_get_started);
        tabIndicator = view.findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getContext(),R.anim.buttonintro_animation);
        tvSkip = view.findViewById(R.id.tv_skip);
        linearLayout1 = view.findViewById(R.id.linearLayout1);


        giioitinh = R.drawable.man;

        imageViewnamnu.setOnClickListener(v -> {
            imageViewnamnu.setBackgroundResource(R.drawable.br_avata);
            imageViewnam.setBackgroundResource(R.drawable.br_date);
            imageViewnu.setBackgroundResource(R.drawable.br_date);
            giioitinh = R.drawable.user;
        });
        imageViewnam.setOnClickListener(v -> {
            imageViewnam.setBackgroundResource(R.drawable.br_avata);
            imageViewnamnu.setBackgroundResource(R.drawable.br_date);
            imageViewnu.setBackgroundResource(R.drawable.br_date);
            giioitinh = R.drawable.man;

        });
        imageViewnu.setOnClickListener(v -> {
            giioitinh = R.drawable.woman;
            imageViewnu.setBackgroundResource(R.drawable.br_avata);
            imageViewnamnu.setBackgroundResource(R.drawable.br_date);
            imageViewnam.setBackgroundResource(R.drawable.br_date);

        });

        final List<ScreenIntroItem> mList = new ArrayList<>();
        mList.add(new ScreenIntroItem("Xin ch??o!","GoodHabits","Ch??o m???ng b???n ?????n v???i GoodHabits, hi v???ng b???n s??? r??n luy???n ???????c nhi???u th??i quen t???t cho b???n th??n nh??!", R.raw.r004));
       // mList.add(new ScreenIntroItem("","Nh???c nh??? h??ng ng??y","???Mu???n ?????ng ??? v??? tr?? kh??ng ph???i ai c??ng ?????ng ???????c, th?? ph???i ch???u ???????c nh???ng th??? kh??ng ph???i ai c??ng c?? th??? tr???i qua??? ",R.drawable.btn_intro_gradienttyle));
        mList.add(new ScreenIntroItem("","Nh???c nh??? h??ng ng??y","Ch??ng t??i s??? nh???c nh??? c??c th??i quen c???a b???n h??ng ng??y, b???n h??y c??i ?????t th???i gian ????? nh???c nh??? b???n th??n h???p l?? nh??! ",R.raw.r003));
        mList.add(new ScreenIntroItem("","Ghi ch??","B???n c??ng c?? th??? l??u l???i c??c ghi ch?? hay ??i???u g?? ???? lm b???n th???y th?? v??? khi ??ang th???c hi???n c??c th??i quen c???a b???n th??n.",R.raw.r002));
        mList.add(new ScreenIntroItem("","B???n ???? s???n s??ng ch??a","Ngay b??y gi??? ho???c kh??ng bao gi???.\nH??y l??m ??i???u g?? ???? ngay h??m nay m?? b???n c???a t????ng lai s??? c???m ??n b???n c???a l??c n??y. Ch??c b???n th??nh c??ng!",R.raw.r001));

        mList.add(new ScreenIntroItem("","hi","ho no",R.drawable.btn_intro_gradienttyle));
        screenPager =view.findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(getContext(),mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1) {
                    loaddLastScreen();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabIndicator.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(v -> {

            position = screenPager.getCurrentItem();
            if (position < mList.size()) {

                position++;
                screenPager.setCurrentItem(position);

            }

            if (position == mList.size()-1) {
                loaddLastScreen();
                //screenPager.setPagingEnabled(false);
            }


        });


        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size()-1) {
                    loaddLastScreen();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        btnGetStarted.setOnClickListener(v -> {
            if (editTextname.getText().equals("")){
                Toast.makeText(getContext(), "B???n ch??a nh???p t??n!!!", Toast.LENGTH_SHORT).show();
            }else {
                
            if (AppPreferences.isIntroData(getContext())){
                AppPreferences.setIntroData(getContext(), editTextname.getText().toString(), giioitinh);
                Navigation.findNavController(getView()).navigateUp();
            }else {
            AppPreferences.setIntroData(getContext(), editTextname.getText().toString(), giioitinh);
            addata();
            AlarmReceiver alarmReceiver = new AlarmReceiver();
                AppPreferences.setTime(getContext(),"07:00");
            alarmReceiver.setRepeatingAlarm(getContext(), AlarmReceiver.TYPE_REPEATING,"07:00", "B???n c??n th???c hi???n c??c th??i quen ch??? ?", "habit10"+"");
            }
            }
        });


        tvSkip.setOnClickListener(v -> {
            screenPager.setCurrentItem(mList.size());
            screenPager.setPagingEnabled(false);

        });


        return  view;
    }

    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        linearLayout1.setVisibility(View.VISIBLE);
        linearLayout1.setAnimation(btnAnim);
        btnGetStarted.setAnimation(btnAnim);

        btnAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                screenPager.setPagingEnabled(false);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void addata() {
        createHatbit_viewModel = ViewModelProviders.of(this).get(CreateHatbit_ViewModel.class);
        mData = new ArrayList<>();
        habit = new Habit();
        mData.clear();
        //hoc tap
        mData.add(new Habit_Add("H???c ngo???i ng??? m???i", "M???i ng??y d??nh 1 gi??? ????? h???c ngo???i ng???","cacacac",R.drawable.x001, "aa", 0,"2", 1));
        mData.add(new Habit_Add("H???c m???t lo???i nh???c c???", "M???i ng??y d??nh 1 gi??? ????? h???c ch??i nh???c c???","cacacac",R.drawable.x002, "aa", 0,"2", 1));
        mData.add(new Habit_Add("L???ng nghe nhi???u h??n", "L???ng nghe m???i ng?????i xung quan trong cu???c s???ng","cacacac",R.drawable.x003, "aa", 0,"2", 1));
        mData.add(new Habit_Add("H???c ?????u t??", "T??m h???u ?????u t?? h??ng ng??y","cacacac",R.drawable.x004, "aa", 0,"2", 1));
        mData.add(new Habit_Add("D??nh th???i gian cho gia ????nh", "D??nh th???i gian cho ng?????i th??n trong gia ????nh nhi???u h??n","cacacac",R.drawable.x005, "aa", 0,"2", 1));
        mData.add(new Habit_Add("?????c s??ch", "acacfa","cacacac",R.drawable.x006, "aa", 0,"2", 1));
//hoc tap

        mData.add(new Habit_Add("Kh??ng h??t thu???c", "B??? thu???c l?? m???i ng??y g???m l?????ng thu???c l?? h??t ??i","cacacac",R.drawable.x007, "aa", 0,"2", 2));
        mData.add(new Habit_Add("Kh??ng ????? ng???t", "Gi???m ??n ????? ng???t h??ng ng??y","cacacac",R.drawable.x008, "aa", 0,"2", 2));
        mData.add(new Habit_Add("Kh??ng ????? ??n nhanh", "H???n ch??? c??c ????? ??n nhanh kh??ng an to??n cho t??u h??a","cacacac",R.drawable.x009, "aa", 0,"2", 2));
        mData.add(new Habit_Add("Suy ngh?? t??ch c???c", "Suy ngh?? t??ch c???c vui v??? trong cu???c s???ng","cacacac",R.drawable.x010, "aa", 0,"2", 2));
       // mData.add(new Habit_Add("Chia s??? y??u th????ng", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 2));
//hoc tap
        mData.add(new Habit_Add("T???p th??? thao", "T???p th??? thao 1 gi??? m???i ng??y v??o b???t k??? th???i gian n??o trong ng??y","cacacac",R.drawable.x011, "aa", 0,"2", 3));
        mData.add(new Habit_Add("Ng???i thi???n", "Ng???i thi???n ????? th?? g??n m???i khi c?? th???","cacacac",R.drawable.x012, "aa", 0,"2", 3));
        mData.add(new Habit_Add("Ch???y b???", "M???i ng??y d??nh 1 gi??? ????? ch???y b???","cacacac",R.drawable.x0131, "aa", 0,"2", 3));
        mData.add(new Habit_Add("?????p xe", "M???i ng??y d??nh 1 gi??? ????? ?????p xe","cacacac",R.drawable.x014, "aa", 0,"2", 3));
       // mData.add(new Habit_Add("T???p h??t th??? s??u", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 3));
//hoc tap
        mData.add(new Habit_Add("??i ng??? s???m", "M???i ng??y ??i ng??? s???m h??n m???t ch??t ????? h??nh th??nh th??i quen ??i ng??? s???m","cacacac",R.drawable.x015, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Kh??ng ng??? n?????ng", "Ng??? n?????ng r???t x???u, c???n r??n luy???n m???i ng?? ????? l???a b???","cacacac",R.drawable.x016, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Qu???n l?? th???i gian", "L??n l???ch cho c??c c??ng vi???c s??? l??m kh??ng ????? th??? gian tr??i qua v?? b??? trong ng??y","cacacac",R.drawable.x017, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Gi???m th???i gian cho MXH", "Gi???m th???i gian cho m???ng x?? h???i h??n v?? s??? d???ng ch??ng khi c???n thi???","cacacac",R.drawable.x018, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Vi???t nh???t k??", "D??nh ch??t th???i gian ????? ghi l???i n???i bu???n, hay n???m vui trong cu???c s???ng","cacacac",R.drawable.x019, "aa", 0,"2", 4));
//hoc tap
//        mData.add(new Habit_Add("abc", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 5));
//        mData.add(new Habit_Add("abc", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 5));
//        mData.add(new Habit_Add("abc", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 5));
//        mData.add(new Habit_Add("abc", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 5));
//        mData.add(new Habit_Add("abc", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 5));

        for (int i = 0; i < mData.size(); i++) {
            habit.habitTitle= mData.get(i).habitTitle;
            habit.habitSubtitle= mData.get(i).habitSubtitle;
            habit.habitIcon= "0";
            habit.habitTime= "08:00";
            habit.habit_Type = mData.get(i).habit_Type;
            habit.habitImage = mData.get(i).habitImage;
            habit.habitStart = 0;
            habit.timestart= "0";
            habit.timeEnd = "0";
            habit.habit_Date=10;

            createHatbit_viewModel.insertHabit(habit);
        }


    }
}