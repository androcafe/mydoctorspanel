package androcafe.visitindia.com.mydoctorspanel;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ForgotPasswordActivity extends AppCompatActivity implements DoctorPanelUrl{

    Button btnSubmit;

    EditText edtEmail;

    RequestQueue requestQueue;

    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSubmit=findViewById(R.id.btn_submit);
        edtEmail=findViewById(R.id.edt_email);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edtEmail.getText().toString();
                checkPassword(email);
            }
        });
    }

    private void checkPassword(final String email) {

        requestQueue= Volley.newRequestQueue(ForgotPasswordActivity.this);
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
                                        flag=1;
                                        sendEmail(username,pass);
                                    }
                                }

                                if(flag==0)
                                {
                                    Toast.makeText(ForgotPasswordActivity.this,"Enter correct email id", Toast.LENGTH_LONG).show();
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

    private void sendEmail(String username, String pass) {

        Log.i("Send email", "");
        String TO=username;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));

        emailIntent.putExtra(Intent.EXTRA_EMAIL, username);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Doctor Panel Account Recovery");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Username : "+username+"\nPassword : "+pass);

        //need this to prompts email client only
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Toast.makeText(ForgotPasswordActivity.this, "Email is sent to your account.", Toast.LENGTH_SHORT).show();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ForgotPasswordActivity.this, "Email is not sent.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ForgotPasswordActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}
