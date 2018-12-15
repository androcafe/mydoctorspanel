package androcafe.visitindia.com.mydoctorspanel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    //UI component declaration
    Button btn_signin,btn_signup;

    //app should close after back button double clicked
    //so at start initialize double clicked of back button as false
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize all widgets and UI components
        findViewByIds();

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/


       //set onClickeListener to Sign In button
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this intent is called to start sign in activity
                Intent intentSignIn=new Intent(LoginActivity.this,SignInActivity.class);
                startActivity(intentSignIn);
                finish();
            }
        });

        //set onCLickListener to Sign Up button
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this intent is called to start sign up activity
                Intent intentSignUp=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intentSignUp);
                finish();
            }
        });
    }

    //Function to Initialize all widgets and UI components
    private void findViewByIds() {
        btn_signin=findViewById(R.id.btn_sign_in);
        btn_signup=findViewById(R.id.btn_sign_up);
    }

    //Function to handle back pressed double clicked
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        //This will set Back Pressed once as true
        this.doubleBackToExitPressedOnce = true;

        //It will generate message to inform user that if he wanted to exit app
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        //This will wait for 2000 milisec for user response
        //If no back pressed response come
        //It will again set back pressed status as false
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
