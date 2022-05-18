package com.trungnvdev.goodhabits.ui.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.trungnvdev.goodhabits.R;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

   Context mContext ;
   List<ScreenIntroItem> mListScreen;

    public IntroViewPagerAdapter(Context mContext, List<ScreenIntroItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_intrroscreen,null);

        if (position < mListScreen.size()-1){
            TextView intro_helo = layoutScreen.findViewById(R.id.intro_helo);
            TextView title = layoutScreen.findViewById(R.id.intro_title);
            TextView description = layoutScreen.findViewById(R.id.intro_description);

            LottieAnimationView lottieAnimationView1= layoutScreen.findViewById(R.id.place);


            if (position == mListScreen.size()-1){

                lottieAnimationView1.setVisibility(View.GONE);
                description.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
                intro_helo.setVisibility(View.GONE);
            }else {
                intro_helo.setText(mListScreen.get(position).getHello());
                title.setText(mListScreen.get(position).getTitle());
                description.setText(mListScreen.get(position).getDescription());
                lottieAnimationView1.setAnimation(mListScreen.get(position).ScreenImg);
                lottieAnimationView1.playAnimation();
                lottieAnimationView1.loop(true);
            }
        }


        container.addView(layoutScreen);

        return layoutScreen;

    }
    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);

    }
}
