package androcafe.visitindia.com.mydoctorspanel.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import visitindia.androcafe.doctorspanel.R;

public class ChangeSchedFragment extends Fragment {

    TextView tv_m1,tv_m2;
    TextView tv_a1,tv_a2,tv_a3,tv_a4;
    TextView tv_e1,tv_e2,tv_e3,tv_e4;

    boolean strTvM1,strTvM2;
    boolean strTvA1,strTvA2,strTvA3,strTvA4;
    boolean strTvE1,strTvE2,strTvE3,strTvE4;

    public static ChangeSchedFragment newInstance() {

        Bundle args = new Bundle();

        ChangeSchedFragment fragment = new ChangeSchedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_change_sched,null);


        //Initialize Textview
        tv_m1=view.findViewById(R.id.tv_m1);
        tv_m2=view.findViewById(R.id.tv_m2);
        tv_a1=view.findViewById(R.id.tv_a1);
        tv_a2=view.findViewById(R.id.tv_a2);
        tv_a3=view.findViewById(R.id.tv_a3);
        tv_a4=view.findViewById(R.id.tv_a4);
        tv_e1=view.findViewById(R.id.tv_e1);
        tv_e2=view.findViewById(R.id.tv_e2);
        tv_e3=view.findViewById(R.id.tv_e3);
        tv_e4=view.findViewById(R.id.tv_e4);

        //onClickListener

        //Morning Appointment
        tv_m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvM1)
                {
                    strTvM1=true;
                    tv_m1.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvM1=false;
                    tv_m1.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        tv_m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvM2)
                {
                    strTvM2=true;
                    tv_m2.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvM2=false;
                    tv_m2.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        //Afternoon Appointment
        tv_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvA1)
                {
                    strTvA1=true;
                    tv_a1.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvA1=false;
                    tv_a1.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        tv_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvA2)
                {
                    strTvA2=true;
                    tv_a2.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvA2=false;
                    tv_a2.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        tv_a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvA3)
                {
                    strTvA3=true;
                    tv_a3.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvA3=false;
                    tv_a3.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        tv_a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvA2)
                {
                    strTvA4=true;
                    tv_a4.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvA4=false;
                    tv_a4.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        //Evening Appointment
        tv_e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvE1)
                {
                    strTvE1=true;
                    tv_e1.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvE1=false;
                    tv_e1.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        tv_e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvE2)
                {
                    strTvE2=true;
                    tv_e2.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvE2=false;
                    tv_e2.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        tv_e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvE3)
                {
                    strTvE3=true;
                    tv_e3.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvE3=false;
                    tv_e3.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });

        tv_e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!strTvE4)
                {
                    strTvE4=true;
                    tv_e4.setTextColor(getResources().getColor(R.color.colorLightBlue));
                }
                else
                {
                    strTvE4=false;
                    tv_e4.setTextColor(getResources().getColor(R.color.colorDarkGray));
                }

            }
        });
        return view;
    }
}
