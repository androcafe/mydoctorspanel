package androcafe.visitindia.com.mydoctorspanel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements DoctorPanelUrl{

    Button btnSignUp;

    EditText editTextEmail,editTextPassword,editTextConfirmPw;

    LinearLayout linearLayout,linearLayout2;

    ImageView ivClose;

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    RequestQueue requestQueue;

    private ProgressDialog progressBar;
    private Handler progressBarbHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH || Build.VERSION.SDK_INT == Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN){
            setContentView(R.layout.activity_sign_up_kitkat);

        }else{
            setContentView(R.layout.activity_sign_up);
        }


        findViewByIds();


        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        if(btnSignUp.hasFocus())
        {
            onSignUp();
        }
        
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSignUp();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void findViewByIds() {
        btnSignUp=findViewById(R.id.btn_sign_up);

        editTextEmail=findViewById(R.id.edt_email);
        editTextPassword=findViewById(R.id.edt_password);
        editTextConfirmPw=findViewById(R.id.edt_cofirm_password);

        linearLayout2=findViewById(R.id.layout_sign_up2);

        ivClose=findViewById(R.id.iv_close);
        requestQueue= Volley.newRequestQueue(SignUpActivity.this);
    }

    private void onSignUp() {
        String email=editTextEmail.getText().toString();
        String password=editTextPassword.getText().toString();
        String confirmPw=editTextConfirmPw.getText().toString();

        if(email.length()>0) {
            int res=emailValidation(email);

            if(res==1)
            {
                int pass_res=passwordValidation(password,confirmPw);

                if(pass_res==1)
                {
                    docSignUp(email,password);
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,"Please enter valid password", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(SignUpActivity.this,"Please enter valid emailId", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(SignUpActivity.this,"Please enter valid emailId", Toast.LENGTH_SHORT).show();
        }

    }

    private int passwordValidation(String password, String confirmPw) {



        if(password.length()<0) {
            editTextPassword.setError("Please Enter valid password");
        }

        if(password.length()>0)
        {
            if(confirmPw.equals(password))
            {
                return 1;
            }
            else
            {
                editTextPassword.setError("Password didn't match");
                editTextConfirmPw.setError("Password didn't match");
                return 0;
            }

        }

        return 0;
    }

    private int emailValidation(String email) {
        EmailValidator();
        boolean res=validate(email);

        if(!res) {
            Toast.makeText(SignUpActivity.this, "Please enter valid emailId", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }

    public void EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String email) {

        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    private void showProgressBar() {
        progressBar = new ProgressDialog(SignUpActivity.this);
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

    private void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(SignUpActivity.this.INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void docSignUp(final String email, final String password) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, SIGN_UP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("response "+response);

                if(response.contains("1"))
                {
                    Toast.makeText(SignUpActivity.this,
                            String.format("User created"), Toast.LENGTH_LONG).show();

                    Intent intentProfile=new Intent(SignUpActivity.this,SignInActivity.class);
                    startActivity(intentProfile);
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,
                            String.format("User not created"), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--ve response "+error.getMessage());
                Toast.makeText(SignUpActivity.this,
                        "Error is " + error.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<String, String>();

                params.put("doct_email",email);
                params.put("doct_password",password);

                return  params;
            }
        };

        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent intent=new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);

    }

}
