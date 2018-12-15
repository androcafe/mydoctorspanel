package androcafe.visitindia.com.mydoctorspanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androcafe.visitindia.com.mydoctorspanel.connection.ConnectionDetector;
import androcafe.visitindia.com.mydoctorspanel.connection.NoConnection;
import visitindia.androcafe.doctorspanel.connection.ConnectionDetector;
import visitindia.androcafe.doctorspanel.connection.NoConnection;

import static androcafe.visitindia.com.mydoctorspanel.SignInActivity.EMAIL;
import static visitindia.androcafe.doctorspanel.SignInActivity.EMAIL;

public class SplashActivity extends AppCompatActivity {

    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    SharedPreferences sharedPreferencesDoc;
    String Id, name;

    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        setContentView(R.layout.activity_splash_screen);

        ivLogo=findViewById(R.id.iv_hospital);

        Animation animation= AnimationUtils.loadAnimation(SplashActivity.this,
                R.anim.splash_screen_anim);
        ivLogo.startAnimation(animation);


        Thread SplashScreen = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    SplashActivity.this.finish();
                    cd = new ConnectionDetector(getApplicationContext());

                    isInternetPresent = cd.isConnectingToInternet();

                    if (isInternetPresent) {

                            sharedPreferencesDoc=getSharedPreferences(SignInActivity.MYDOCPREF,MODE_PRIVATE);

                            if(sharedPreferencesDoc.contains(EMAIL))
                            {
                                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                                finish();
                            }
                            else
                            {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                finish();
                            }


                    }
                    else
                    {
                        SplashActivity.this.finish();
                        startActivity(new Intent(SplashActivity.this, NoConnection.class));
                    }

                }
            }
        };
        SplashScreen.start();
    }
}
