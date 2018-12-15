package androcafe.visitindia.com.mydoctorspanel.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import visitindia.androcafe.doctorspanel.HomeActivity;
import visitindia.androcafe.doctorspanel.LoginActivity;
import visitindia.androcafe.doctorspanel.R;
import visitindia.androcafe.doctorspanel.SignInActivity;
import visitindia.androcafe.doctorspanel.changesched.ChangeScheduleActivity;
import visitindia.androcafe.doctorspanel.model.Home;
import visitindia.androcafe.doctorspanel.todayshedule.TodaysScheduleActivity;

public class HomeFragment extends Fragment {

    //Home model class
    Home home;

    //List of type home
    List<Home> listHome=new ArrayList<>();

    LinearLayout ll_cancel_apmt,ll_change_sched,ll_today_apmt;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,null);

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Home");

        ll_cancel_apmt=view.findViewById(R.id.ll_cancel_apmt);
        ll_change_sched=view.findViewById(R.id.ll_change_apmt);
        ll_today_apmt=view.findViewById(R.id.ll_today_apmt);



       ll_today_apmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToadySched=new Intent(getActivity(),TodaysScheduleActivity.class);
                startActivity(intentToadySched);
            }
        });

       ll_change_sched.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intentChangeSched=new Intent(getActivity(), ChangeScheduleActivity.class);
               startActivity(intentChangeSched);
           }
       });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Confirm this fragment has menu items.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==R.id.menu_logout)
        {
            showLogoutDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLogoutDialog() {

        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        builder.setMessage("Do you want to logout?");

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                SharedPreferences sharedPreferences=getActivity().getSharedPreferences(SignInActivity.MYDOCPREF,0);

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(SignInActivity.EMAIL,"");

                editor.commit();

                Intent intentLogin=new Intent(getActivity(), LoginActivity.class);
                startActivity(intentLogin);

            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
