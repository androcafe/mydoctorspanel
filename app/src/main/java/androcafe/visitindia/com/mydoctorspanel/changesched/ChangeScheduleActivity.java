package androcafe.visitindia.com.mydoctorspanel.changesched;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.fragment.ChangeSchedFragment;

public class ChangeScheduleActivity extends AppCompatActivity {

    TextView tvM,tvT,tvW,tvTh,tvF,tvS,tvSu;

    FrameLayout frameLayout;

    Fragment fragmentChangeSched=null;

    String day="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_schedule);

        getSupportActionBar().setTitle("Change Schedule");

        //Initialize Widgets

        tvM=findViewById(R.id.tv_mon);
        tvT=findViewById(R.id.tv_tue);
        tvW=findViewById(R.id.tv_wed);
        tvTh=findViewById(R.id.tv_thu);
        tvF=findViewById(R.id.tv_fri);
        tvS=findViewById(R.id.tv_sat);
        tvSu=findViewById(R.id.tv_sun);

        frameLayout=findViewById(R.id.framelayout_Schedule);

        tvM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="Monday";

                tvM.setBackgroundResource(R.color.colorSkyBlue);

                tvT.setBackgroundResource(R.color.colorWhite);
                tvW.setBackgroundResource(R.color.colorWhite);
                tvTh.setBackgroundResource(R.color.colorWhite);
                tvF.setBackgroundResource(R.color.colorWhite);
                tvS.setBackgroundResource(R.color.colorWhite);
                tvSu.setBackgroundResource(R.color.colorWhite);

                fragmentChangeSched= ChangeSchedFragment.newInstance();
                loadFragment(fragmentChangeSched);
            }
        });

        tvT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="Tuesday";

                tvT.setBackgroundResource(R.color.colorSkyBlue);

                tvM.setBackgroundResource(R.color.colorWhite);
                tvW.setBackgroundResource(R.color.colorWhite);
                tvTh.setBackgroundResource(R.color.colorWhite);
                tvF.setBackgroundResource(R.color.colorWhite);
                tvS.setBackgroundResource(R.color.colorWhite);
                tvSu.setBackgroundResource(R.color.colorWhite);

                fragmentChangeSched= ChangeSchedFragment.newInstance();
                loadFragment(fragmentChangeSched);
            }
        });
        tvW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="Wednesday";

                tvW.setBackgroundResource(R.color.colorSkyBlue);

                tvM.setBackgroundResource(R.color.colorWhite);
                tvT.setBackgroundResource(R.color.colorWhite);
                tvTh.setBackgroundResource(R.color.colorWhite);
                tvF.setBackgroundResource(R.color.colorWhite);
                tvS.setBackgroundResource(R.color.colorWhite);
                tvSu.setBackgroundResource(R.color.colorWhite);

                fragmentChangeSched= ChangeSchedFragment.newInstance();
                loadFragment(fragmentChangeSched);

            }
        });
        tvTh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="Thursday";

                tvTh.setBackgroundResource(R.color.colorSkyBlue);

                tvM.setBackgroundResource(R.color.colorWhite);
                tvT.setBackgroundResource(R.color.colorWhite);
                tvW.setBackgroundResource(R.color.colorWhite);
                tvF.setBackgroundResource(R.color.colorWhite);
                tvS.setBackgroundResource(R.color.colorWhite);
                tvSu.setBackgroundResource(R.color.colorWhite);

                fragmentChangeSched= ChangeSchedFragment.newInstance();
                loadFragment(fragmentChangeSched);
            }
        });
        tvF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="Friday";

                tvF.setBackgroundResource(R.color.colorSkyBlue);

                tvM.setBackgroundResource(R.color.colorWhite);
                tvT.setBackgroundResource(R.color.colorWhite);
                tvW.setBackgroundResource(R.color.colorWhite);
                tvTh.setBackgroundResource(R.color.colorWhite);
                tvS.setBackgroundResource(R.color.colorWhite);
                tvSu.setBackgroundResource(R.color.colorWhite);

                fragmentChangeSched= ChangeSchedFragment.newInstance();
                loadFragment(fragmentChangeSched);
            }
        });
        tvS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="Satday";

                tvS.setBackgroundResource(R.color.colorSkyBlue);

                tvM.setBackgroundResource(R.color.colorWhite);
                tvT.setBackgroundResource(R.color.colorWhite);
                tvW.setBackgroundResource(R.color.colorWhite);
                tvF.setBackgroundResource(R.color.colorWhite);
                tvTh.setBackgroundResource(R.color.colorWhite);
                tvSu.setBackgroundResource(R.color.colorWhite);

                fragmentChangeSched= ChangeSchedFragment.newInstance();
                loadFragment(fragmentChangeSched);
            }
        });
        tvSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                day="Sunday";

                tvSu.setBackgroundResource(R.color.colorSkyBlue);

                tvM.setBackgroundResource(R.color.colorWhite);
                tvT.setBackgroundResource(R.color.colorWhite);
                tvW.setBackgroundResource(R.color.colorWhite);
                tvF.setBackgroundResource(R.color.colorWhite);
                tvS.setBackgroundResource(R.color.colorWhite);
                tvTh.setBackgroundResource(R.color.colorWhite);

                fragmentChangeSched= ChangeSchedFragment.newInstance();
                loadFragment(fragmentChangeSched);
            }
        });

    }

    private void loadFragment(Fragment fragmentChangeSched) {
        if(fragmentChangeSched!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_Schedule,fragmentChangeSched)
                    .commit();
        }
    }
}
