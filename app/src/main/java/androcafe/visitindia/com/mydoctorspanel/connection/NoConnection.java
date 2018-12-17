package androcafe.visitindia.com.mydoctorspanel.connection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
;import androcafe.visitindia.com.mydoctorspanel.R;

public class NoConnection extends AppCompatActivity {

	//ConnectionDetector cd;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_no_connection);

		TextView txtExit= findViewById(R.id.tv_exit);
		txtExit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
