package com.trungnvdev.goodhabits.util;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.navigation.Navigation;

public class DataBindingAdapters {

    @BindingAdapter("navAction")
    public static void setNavAction(final View view, final int actionId) {
        if (view != null) {
            view.setOnClickListener(view1 -> {
                Navigation.findNavController(view).navigate(actionId);
                //Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.action_start2);
            });
        }
    }

    @BindingAdapter({"setIamge"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
