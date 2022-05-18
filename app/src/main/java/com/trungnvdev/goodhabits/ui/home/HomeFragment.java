package com.trungnvdev.goodhabits.ui.home;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.TimePicker;

import com.airbnb.lottie.LottieAnimationView;
import com.trungnvdev.goodhabits.databinding.FragmentHomeBinding;
import com.trungnvdev.goodhabits.util.receiver.AlarmReceiver;
import com.trungnvdev.goodhabits.data.Perferences.AppPreferences;
import com.trungnvdev.goodhabits.R;
import com.trungnvdev.goodhabits.util.SnackbarUtils;
import com.trungnvdev.goodhabits.util.StartIntentUtils;


public class HomeFragment extends Fragment {


    DrawerLayout drawerLayout;
    public static final String EVENT_NOTIFICATION_ID = "EVENT_NOTIFICATION";
    private final static String PACKAGE_NAME = "goodhabits";

    private FragmentHomeBinding mBinding;
    private HomeViewModel mViewModel;
    private BackPressedHandler backPressedHandler;
    private LottieAnimationView lottieAnimationView1;
    private ImageOnclick imageOnclick;
    private final static String EMAIL_ADDRESS = "mailto:nvtr.dev@gmail.com";
    private final static String EMAIL_SUBJECT = "{Good_Habits} Feedback";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!(getActivity() instanceof BackPressedHandler)) {
            throw new ClassCastException("Hosting activity must implement BackHandlerInterface");
        } else {
            backPressedHandler = (BackPressedHandler) getActivity();
        }

    }
    public boolean onBackPressed() {
            return false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewModel = ViewModelProviders.of(this).get( HomeViewModel.class);
        mBinding = FragmentHomeBinding.bind(view);
        mBinding.setViewmodel(mViewModel);
        mBinding.setLifecycleOwner(this);

        Toolbar toolbar = mBinding.toolbarmain;
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_menu);



        drawerLayout = mBinding.drawerlayout;
        toolbar.setNavigationOnClickListener(v ->  {
            drawerLayout.openDrawer(GravityCompat.START);
            lottieAnimationView1= mBinding.place;
            mBinding.timesss.setText(AppPreferences.getTime(getContext()));
            lottieAnimationView1.playAnimation();
            lottieAnimationView1.loop(true);

        });

        setupdrawer();
        setAcc();
        setStartHabit();



        return view;
    }

    private void setupdrawer() {

        mBinding.linetime.setOnClickListener(v -> {

            drawerLayout.close();
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
                    mBinding.timesss.setText(hourday+":"+minuteday);
                    AppPreferences.setTime(getContext(),hourday+":"+minuteday );
                    AlarmReceiver alarmReceiver = new AlarmReceiver();
                    alarmReceiver.cancelAlarm(getContext(),AlarmReceiver.TYPE_REPEATING,"habit10"+"");
                    alarmReceiver.setRepeatingAlarm(getContext(), AlarmReceiver.TYPE_REPEATING,hourday+":"+minuteday, "Bạn còn thực hiện các thói quen chứ ?", "habit10"+"");
                }
            }, 8, 0, true);
            timePickerDialog.show();

        });
        mBinding.lineintro.setOnClickListener(v -> {
            drawerLayout.close();
            Navigation.findNavController(getView()).navigate(R.id.action_intro);
        });

        mBinding.linestar.setOnClickListener(v -> {
            drawerLayout.close();
                        boolean isSucceed = StartIntentUtils.startIntentUrl(getActivity(), "market://details?id=com.trungnvdev.goodhabits" + PACKAGE_NAME);
                        if (!isSucceed) {
                            SnackbarUtils.showSnackbar(getView(), getResources().getString(R.string.failed_to_open_app_store));
                        }
        });
        mBinding.linefeedback.setOnClickListener(v -> {

            drawerLayout.close();
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse(EMAIL_ADDRESS));
                    intent.putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT);
                    if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                        startActivity(intent);
                    }else {
                        SnackbarUtils.showSnackbar(getView(), getResources().getString(R.string.no_email_client));
                    }
        });

        mBinding.linenfo.setOnClickListener(v -> {
            drawerLayout.close();
            final Dialog dialog = new Dialog( getContext());
            dialog.requestWindowFeature (Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_info);

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
            TextView cacel = dialog.findViewById(R.id.close);
            cacel.setOnClickListener(v1 -> {
                dialog.dismiss();
            });
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        backPressedHandler.setSelectedFragment(this);
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
    private void setAcc() {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayoutacount, new Acount_Fragment());
        transaction.commit();
    }

    private void setStartHabit() {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayoutstart, new Habitstart_Fragment());
        transaction.commit();
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        if (getArguments() != null && !getArguments().isEmpty()) {
        inflater.inflate(R.menu.photo_br, menu);
        // }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ImageOnclick) {
            imageOnclick = (ImageOnclick) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        imageOnclick = null;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.photo){
          imageOnclick.onclick();
        }
        return true;
    }
}