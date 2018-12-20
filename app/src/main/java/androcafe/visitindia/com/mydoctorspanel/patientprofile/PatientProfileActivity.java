package androcafe.visitindia.com.mydoctorspanel.patientprofile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androcafe.visitindia.com.mydoctorspanel.R;


public class PatientProfileActivity extends AppCompatActivity {
    //Declare all widgets and UI components
    FrameLayout frameLayout;

    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        getSupportActionBar().setTitle(getResources().getString(R.string.string_patient_profile));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize all Ui component
        frameLayout=findViewById(R.id.framelayout_patient_profile);

        //set framelayout to patient profile fragment by default
        fragment=PatientProfileHomepgFragment.newInstance();

        //load fragment to framelayout
        loadFragment(fragment);

    }

    private void loadFragment(Fragment fragment) {
        if(fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_patient_profile,fragment)
                    .commit();
        }
    }


}
