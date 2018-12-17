package androcafe.visitindia.com.mydoctorspanel.cancelappoint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.model.CancelApmt;


public class CancelApmtActivity extends AppCompatActivity {

    ListView lvCancelApmt;

    CancelApmt cancelApmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_apmt);

        getSupportActionBar().setTitle("Cancel Appointment");

        findViewByIds();

        getAllApmtDetails();
    }

    private void findViewByIds() {

        lvCancelApmt=findViewById(R.id.lv_cancel_apmt);
    }

    private void getAllApmtDetails() {

    }
}
