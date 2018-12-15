package androcafe.visitindia.com.mydoctorspanel.patientprofile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import visitindia.androcafe.doctorspanel.R;

public class PatientProfileActivity extends AppCompatActivity {
    //Declare all widgets and UI components
    FrameLayout frameLayout;

    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

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
