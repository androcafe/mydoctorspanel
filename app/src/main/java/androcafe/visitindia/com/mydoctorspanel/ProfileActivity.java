package androcafe.visitindia.com.mydoctorspanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.List;

import androcafe.visitindia.com.mydoctorspanel.profilefragment.BasicInfoFragment;
import androcafe.visitindia.com.mydoctorspanel.profilefragment.BiographyFragment;
import androcafe.visitindia.com.mydoctorspanel.profilefragment.CertificateFragment;
import androcafe.visitindia.com.mydoctorspanel.profilefragment.EducationFragment;

public class ProfileActivity extends AppCompatActivity {

    FrameLayout frameLayoutProfile;

    Fragment fragmentProfile = null;

    //Pref object
    SharedPreferences sharedPreferences;

    //Pref fields
    public static String ISFIRSTTIME="IsFirstTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Sharedpreference Initialization
        sharedPreferences=getSharedPreferences(SignInActivity.MYDOCPREF,MODE_PRIVATE);

        //Editor to edit sharedpref files
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(ISFIRSTTIME,"True");
        editor.commit();

        findViewByIds();

        fragmentProfile = BasicInfoFragment.newInstance();
        loadFragment(fragmentProfile);
    }

    private void loadFragment(Fragment fragmentProfile) {
        if (fragmentProfile != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout_profile, fragmentProfile)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void findViewByIds() {

        frameLayoutProfile = findViewById(R.id.framelayout_profile);
    }

    @Override
    public void onBackPressed() {
        tellFragments();
        super.onBackPressed();
    }

    private void tellFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment f : fragments) {
            if (f != null && f instanceof CertificateFragment)
                ((CertificateFragment) f).onBackPressed();
            if (f != null && f instanceof BiographyFragment)
                ((BiographyFragment) f).onBackPressed();
            if (f != null && f instanceof EducationFragment)
                ((EducationFragment) f).onBackPressed();
            if (f != null && f instanceof BasicInfoFragment) {
                ((BasicInfoFragment) f).onBackPressed();
                Intent intentHome = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intentHome);
            }
        }
    }
}
