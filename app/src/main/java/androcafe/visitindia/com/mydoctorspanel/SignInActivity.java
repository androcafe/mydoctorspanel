package androcafe.visitindia.com.mydoctorspanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity implements DoctorPanelUrl{
    //****Widgets and UI component declaration****
    Button buttonSignIn;
    TextView tvCreateNow,tvForgotPw;
    EditText edtEmail,edtPassword;

    //Linear layout declaration
    //here layout object is taken to hide keyboard if it is present on screen
    LinearLayout layoutSignIn,layoutSignIn2;

    private ProgressDialog progressBar;
    private Handler progressBarbHandler = new Handler();

    //Volley RequestQueue Declaration
    //Used to add all requests into queue so that volley can process them
    RequestQueue requestQueue;

    //****Shared Preference****
    //Pref name
    public static String MYDOCPREF="MyDocPref";

    //Pref fields
    public static String EMAIL="Email";
    public static String DID="Did";

    //Pref object
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH || Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN){
            setContentView(R.layout.activity_sign_in_kitkat);

        }else{
            setContentView(R.layout.activity_sign_in);
        }



        //Initialize all widgets and UI components
        findViewByIds();


        layoutSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        layoutSignIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        //set onCLickListener to Sign IN button
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSignIn.setEnabled(false);

                String email=edtEmail.getText().toString();
                String password=edtPassword.getText().toString();

                int flag=0;
                if(email.equals(""))
                {
                    edtEmail.setError("Enter correct email");
                    buttonSignIn.setEnabled(true);
                    flag=1;
                }
                if(password.equals(""))
                {
                    edtPassword.setError("Enter correct password");
                    buttonSignIn.setEnabled(true);
                    flag=1;
                }

                if(flag==0)
                {
                    email=email.trim();
                    password=password.trim();
                    if(isNetworkAvailable())
                    {
                        showProgressBar();
                        validateDoctor(email,password);
                    }
                    else
                    {
                        Toast.makeText(SignInActivity.this,"Network is unavailable", Toast.LENGTH_LONG).show();
                    }

                }
         }
        });

        tvCreateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intentSignUp);
                finish();
            }
        });

        tvForgotPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    //Function to Initialize all widgets and UI components
    private void findViewByIds() {

        //Initialize requestqueue
        requestQueue= Volley.newRequestQueue(SignInActivity.this);

        layoutSignIn=findViewById(R.id.layout_sign_in);
        layoutSignIn2=findViewById(R.id.layout_sign_in2);

        edtEmail=findViewById(R.id.edt_email);
        edtPassword=findViewById(R.id.edt_password);

        buttonSignIn=findViewById(R.id.btn_sign_in);

        tvCreateNow=findViewById(R.id.tv_create_now);
        tvForgotPw=findViewById(R.id.tv_forgot_pw);
    }


    private void validateDoctor(final String email, final String password) {


                JSONObject objl = new JSONObject();
                try {
                    objl.put("doct_email", email);
                    objl.put("doct_password", password);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println("email _ pass "+ objl.toString());

                JsonObjectRequest jsonReqlog = new JsonObjectRequest(
                        Request.Method.POST, SIGN_IN_URL, null,
                        new Response.Listener<JSONObject>() {

                            public void onResponse(JSONObject responselog) {
                                if (responselog != null) {
                                    try {
                                        int flag=0;
                                        JSONArray jsonArray=responselog.getJSONArray("users");

                                        for(int i=0;i<jsonArray.length();i++)
                                        {
                                            JSONObject jsonObject=jsonArray.getJSONObject(i);

                                            String did=jsonObject.getString("id");
                                            String username=jsonObject.getString("doct_email");
                                            String pass= jsonObject.getString("doct_password");

                                            if(username.equals(email))
                                            {
                                                if(pass.equals(password))
                                                {
                                                    flag=1;

                                                    System.out.println("did "+did);
                                                    saveDataToSharedPref(email,did);

                                                    buttonSignIn.setEnabled(true);

                                                    progressBar.dismiss();

                                                    startNewActivity();
                                                }
                                            }
                                        }

                                        if(flag==0)
                                        {
                                            buttonSignIn.setEnabled(true);
                                            clear();
                                            progressBar.dismiss();
                                            Toast.makeText(SignInActivity.this,"Incorrect Credential", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }

                        }, new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Error "+error.toString());
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("doct_email", email);
                        map.put("doct_password", password);
                        return map;
                    }
                };

                requestQueue.add(jsonReqlog);
    }

    private void startNewActivity() {


        String isFirstTime=sharedPreferences.getString(ProfileActivity.ISFIRSTTIME,null);

        if(sharedPreferences.contains(ProfileActivity.ISFIRSTTIME))
        {
            if(isFirstTime.equals("True"))
            {
                Intent intentProfile=new Intent(SignInActivity.this,HomeActivity.class);
                startActivity(intentProfile);
                finish();
                Toast.makeText(SignInActivity.this,"Login Successfully", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intentProfile=new Intent(SignInActivity.this,ProfileActivity.class);
                startActivity(intentProfile);
                finish();
                Toast.makeText(SignInActivity.this,"Login Successfully", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Intent intentProfile=new Intent(SignInActivity.this,ProfileActivity.class);
            startActivity(intentProfile);
            finish();
            Toast.makeText(SignInActivity.this,"Login Successfully", Toast.LENGTH_LONG).show();
        }
    }

    private void saveDataToSharedPref(String email, String did) {
        //Sharedpreference Initialization
        sharedPreferences=getSharedPreferences(MYDOCPREF,MODE_PRIVATE);

        //Editor to edit sharedpref files
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(EMAIL,email);
        editor.putString(DID,did);

        //commit to save changes in editor
        editor.commit();
    }

    private void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(SignInActivity.this.INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(SignInActivity.this,HomeActivity.class);
                startActivity(intent);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    public void clear() {
        edtEmail.setText("");
        edtPassword.setText("");
    }

    private void showProgressBar() {
        progressBar = new ProgressDialog(SignInActivity.this);
        progressBar.setMessage("Login ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.setCancelable(false);
        progressBar.show();
        final int[] progressBarStatus = {0};

        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus[0] < 100) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressBarStatus[0]);
                        }
                    });
                }

            }
        }).start();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(SignInActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent=new Intent(SignInActivity.this,LoginActivity.class);
        startActivity(intent);

    }

}
