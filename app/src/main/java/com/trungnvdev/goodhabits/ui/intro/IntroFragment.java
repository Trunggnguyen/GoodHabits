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
        mList.add(new ScreenIntroItem("Xin chào!","GoodHabits","Chào mừng bạn đến với GoodHabits, hi vọng bạn sẽ rèn luyện được nhiều thói quen tốt cho bản thân nhé!", R.raw.r004));
       // mList.add(new ScreenIntroItem("","Nhắc nhở hàng ngày","“Muốn đứng ở vị trí không phải ai cũng đứng được, thì phải chịu được những thứ không phải ai cũng có thể trải qua” ",R.drawable.btn_intro_gradienttyle));
        mList.add(new ScreenIntroItem("","Nhắc nhở hàng ngày","Chúng tôi sẽ nhắc nhở các thói quen của bạn hàng ngày, bạn hãy cài đặt thời gian để nhắc nhở bản thân hợp lý nhé! ",R.raw.r003));
        mList.add(new ScreenIntroItem("","Ghi chú","Bạn cũng có thể lưu lại các ghi chú hay điều gì đó lm bạn thấy thú vị khi đang thực hiện các thói quen của bản thân.",R.raw.r002));
        mList.add(new ScreenIntroItem("","Bạn đã sẵn sàng chưa","Ngay bây giờ hoặc không bao giờ.\nHãy làm điều gì đó ngay hôm nay mà bạn của tương lai sẽ cảm ơn bạn của lúc này. Chúc bạn thành công!",R.raw.r001));

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
                Toast.makeText(getContext(), "Bạn chưa nhập tên!!!", Toast.LENGTH_SHORT).show();
            }else {
                
            if (AppPreferences.isIntroData(getContext())){
                AppPreferences.setIntroData(getContext(), editTextname.getText().toString(), giioitinh);
                Navigation.findNavController(getView()).navigateUp();
            }else {
            AppPreferences.setIntroData(getContext(), editTextname.getText().toString(), giioitinh);
            addata();
            AlarmReceiver alarmReceiver = new AlarmReceiver();
                AppPreferences.setTime(getContext(),"07:00");
            alarmReceiver.setRepeatingAlarm(getContext(), AlarmReceiver.TYPE_REPEATING,"07:00", "Bạn còn thực hiện các thói quen chứ ?", "habit10"+"");
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
        mData.add(new Habit_Add("Học ngoại ngữ mới", "Mỗi ngày dành 1 giờ để học ngoại ngữ","cacacac",R.drawable.x001, "aa", 0,"2", 1));
        mData.add(new Habit_Add("Học một loại nhạc cụ", "Mỗi ngày dành 1 giờ để học chơi nhạc cụ","cacacac",R.drawable.x002, "aa", 0,"2", 1));
        mData.add(new Habit_Add("Lắng nghe nhiều hơn", "Lắng nghe mọi người xung quan trong cuộc sống","cacacac",R.drawable.x003, "aa", 0,"2", 1));
        mData.add(new Habit_Add("Học đầu tư", "Tìm hểu đầu tư hàng ngày","cacacac",R.drawable.x004, "aa", 0,"2", 1));
        mData.add(new Habit_Add("Dành thời gian cho gia đình", "Dành thời gian cho người thân trong gia đình nhiều hơn","cacacac",R.drawable.x005, "aa", 0,"2", 1));
        mData.add(new Habit_Add("Đọc sách", "acacfa","cacacac",R.drawable.x006, "aa", 0,"2", 1));
//hoc tap

        mData.add(new Habit_Add("Không hút thuốc", "Bỏ thuốc lá mỗi ngày gảm lượng thuốc lá hút đi","cacacac",R.drawable.x007, "aa", 0,"2", 2));
        mData.add(new Habit_Add("Không đồ ngọt", "Giảm ăn đồ ngọt hàng ngày","cacacac",R.drawable.x008, "aa", 0,"2", 2));
        mData.add(new Habit_Add("Không đồ ăn nhanh", "Hạn chế các đồ ăn nhanh không an toàn cho têu hóa","cacacac",R.drawable.x009, "aa", 0,"2", 2));
        mData.add(new Habit_Add("Suy nghĩ tích cực", "Suy nghĩ tích cực vui vẻ trong cuộc sống","cacacac",R.drawable.x010, "aa", 0,"2", 2));
       // mData.add(new Habit_Add("Chia sẻ yêu thương", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 2));
//hoc tap
        mData.add(new Habit_Add("Tập thể thao", "Tập thể thao 1 giờ mỗi ngày vào bất kỳ thời gian nào trong ngày","cacacac",R.drawable.x011, "aa", 0,"2", 3));
        mData.add(new Habit_Add("Ngồi thiền", "Ngồi thiền để thư gãn mỗi khi có thể","cacacac",R.drawable.x012, "aa", 0,"2", 3));
        mData.add(new Habit_Add("Chạy bộ", "Mỗi ngày dành 1 giờ để chạy bộ","cacacac",R.drawable.x0131, "aa", 0,"2", 3));
        mData.add(new Habit_Add("Đạp xe", "Mỗi ngày dành 1 giờ để đạp xe","cacacac",R.drawable.x014, "aa", 0,"2", 3));
       // mData.add(new Habit_Add("Tập hít thở sâu", "acacfa","cacacac",R.drawable.br_addhatbit, "aa", 0,"2", 3));
//hoc tap
        mData.add(new Habit_Add("Đi ngủ sớm", "Mỗi ngày đi ngủ sớm hơn một chút để hình thành thói quen đi ngủ sớm","cacacac",R.drawable.x015, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Không ngủ nướng", "Ngủ nướng rất xấu, cần rèn luyện mỗi ngà để lọa bỏ","cacacac",R.drawable.x016, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Quản lý thời gian", "Lên lịch cho các công việc sẽ làm không để thờ gian trôi qua vô bổ trong ngày","cacacac",R.drawable.x017, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Giảm thời gian cho MXH", "Giảm thời gian cho mạng xã hội hơn và sử dụng chúng khi cần thiế","cacacac",R.drawable.x018, "aa", 0,"2", 4));
        mData.add(new Habit_Add("Viết nhật ký", "Dành chút thời gian để ghi lại nỗi buồn, hay nềm vui trong cuộc sống","cacacac",R.drawable.x019, "aa", 0,"2", 4));
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