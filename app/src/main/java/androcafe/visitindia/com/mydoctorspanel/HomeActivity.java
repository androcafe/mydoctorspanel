package androcafe.visitindia.com.mydoctorspanel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import visitindia.androcafe.doctorspanel.fragment.AppointmentStatusFragment;
import visitindia.androcafe.doctorspanel.fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Fragment fragment=null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment= HomeFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    fragment= AppointmentStatusFragment.newInstance();
                    break;
                case R.id.navigation_notifications:
                    fragment= AppointmentStatusFragment.newInstance();
                    break;
            }
            loadFragment(fragment);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        frameLayout=findViewById(R.id.framelayout);

        fragment= HomeFragment.newInstance();
        loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayout,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}
