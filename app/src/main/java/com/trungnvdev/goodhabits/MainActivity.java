package com.trungnvdev.goodhabits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.trungnvdev.goodhabits.data.Perferences.AppPreferences;
import com.trungnvdev.goodhabits.databinding.ActivityMainBinding;
import com.trungnvdev.goodhabits.ui.home.BackPressedHandler;
import com.trungnvdev.goodhabits.ui.home.HomeFragment;
import com.trungnvdev.goodhabits.ui.home.ImageOnclick;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements BackPressedHandler, ImageOnclick {


    private HomeFragment homeFragment;
    ActivityMainBinding activityMainBinding;
    ConstraintLayout relativeLayout;
    List<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.rootview);
        setupIntroPage();
        setbr();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    private void setbr() {
        list = new ArrayList<>();
        list.add(R.drawable.z001);
        list.add(R.drawable.z002);
        list.add(R.drawable.z003);
        list.add(R.drawable.z001);
        list.add(R.drawable.z005);
        list.add(R.drawable.z006);
        list.add(R.drawable.z007);
        list.add(R.drawable.z008);
        list.add(R.drawable.z009);
        list.add(R.drawable.z010);
        list.add(R.drawable.z011);
        list.add(R.drawable.z012);
        list.add(R.drawable.z014);
        list.add(R.drawable.z015);
        list.add(R.drawable.z016);
        list.add(R.drawable.z017);
        list.add(R.drawable.z018);
        list.add(R.drawable.z019);
        list.add(R.drawable.z020);
        list.add(R.drawable.z021);
        list.add(R.drawable.z022);
        list.add(R.drawable.z023);
        list.add(R.drawable.z024);
        list.add(R.drawable.z025);
        list.add(R.drawable.z026);
        list.add(R.drawable.z027);
        list.add(R.drawable.z028);
        list.add(R.drawable.z029);
        list.add(R.drawable.z030);
        list.add(R.drawable.z031);
        list.add(R.drawable.z032);
        list.add(R.drawable.z033);
        list.add(R.drawable.z034);
        list.add(R.drawable.z035);
        list.add(R.drawable.z035);

        int min = 1;
        int max = 35;
        Random r = new Random();
        int i1 = r.nextInt(max - min + 1) + min;
        relativeLayout.setBackgroundResource(list.get(i1-1));

    }

    @Override
        public boolean onSupportNavigateUp() {
            return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
        }

        @Override
        public void onBackPressed() {
        if(AppPreferences.isIntroData(this)){
            if (homeFragment == null  || !homeFragment.onBackPressed()|| !homeFragment.isVisible()) {
                super.onBackPressed();
            }
        }

        }
    private void setupIntroPage() {
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_start);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!AppPreferences.isIntroData(MainActivity.this)) {
                    Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigateUp();
                    Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigate(R.id.action_intro);
                }else {
                    Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment).navigateUp();
                }
            }
        }, 3000);
    }

    @Override
    public void setSelectedFragment(HomeFragment fragment) {
        homeFragment = fragment;

    }

    @Override
    public void onclick() {
        setbr();
    }
}
