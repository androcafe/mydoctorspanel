package androcafe.visitindia.com.mydoctorspanel.profilefragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import visitindia.androcafe.doctorspanel.DoctorPanelUrl;
import visitindia.androcafe.doctorspanel.HomeActivity;
import visitindia.androcafe.doctorspanel.OnBackPressed;
import visitindia.androcafe.doctorspanel.R;
import visitindia.androcafe.doctorspanel.SignInActivity;

public class BiographyFragment extends Fragment implements OnBackPressed,DoctorPanelUrl {

    EditText edtBio;

    Button btnSave;

    RelativeLayout rlbio;
    LinearLayout llbio1,llbio2;

    SharedPreferences sharedPreferences;

    RequestQueue requestQueue;

    public static BiographyFragment newInstance() {

        Bundle args = new Bundle();

        BiographyFragment fragment = new BiographyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_bio,null);

        requestQueue= Volley.newRequestQueue(getContext());

        sharedPreferences=getActivity().getSharedPreferences(SignInActivity.MYDOCPREF, Context.MODE_PRIVATE);

        edtBio=view.findViewById(R.id.edt_bio);

        btnSave=view.findViewById(R.id.btn_save);

        rlbio=view.findViewById(R.id.rl_bio);
        llbio1=view.findViewById(R.id.ll_bio1);
        llbio2=view.findViewById(R.id.ll_bio2);

        rlbio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        llbio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        llbio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                saveDocProfileData();

            }
        });
        return view;
    }

    private void saveDocProfileData() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, INSERT_PROFILE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("+ve response");

                Toast.makeText(getActivity(),
                        String.format("Doctor information is stored successfully"), Toast.LENGTH_LONG).show();

                Intent intentHome=new Intent(getActivity(), HomeActivity.class);
                startActivity(intentHome);
                getActivity().finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--ve response "+error.getMessage());
                Toast.makeText(getActivity(),
                        "Error is " + error.getMessage()
                        , Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<String, String>();

                String did=sharedPreferences.getString(SignInActivity.DID,null);
                String photo=sharedPreferences.getString("PHOTO",null);
                String name=sharedPreferences.getString("NAME",null);
                String address=sharedPreferences.getString("ADDRESS",null);
                String phoneno=sharedPreferences.getString("PHONENO",null);
                String certificate=sharedPreferences.getString("CERTIFICATE",null);
                String education=sharedPreferences.getString("EDUCATION",null);
                String specialize=sharedPreferences.getString("SPECIALIZE",null);

                String bio="";
                if(edtBio.getText().toString().length()>0)
                {
                    bio=edtBio.getText().toString();
                }

                String created_at=getTodaysDate();

                System.out.println("Did "+did+"\nPhoto "+photo+"\nName "+name+"\nAddress "+address+"\nPhone "+phoneno+"\ncertificate "+certificate+"\nEducation "+education+"\nSpecialize "+specialize+"\nBio "+bio+"\nCreated At "+created_at);

                params.put("did",did);
                params.put("doct_photo",photo);
                params.put("doct_name",name);
                params.put("doct_address",address);
                params.put("doct_phone_no",phoneno);
                params.put("doct_area_of_expertise",specialize);
                params.put("doct_professional_biography",bio);
                params.put("doct_education",education);
                params.put("doct_certificates",certificate);
                params.put("created_at",created_at);

                return  params;
            }
        };

        requestQueue.add(stringRequest);
    }


    //to get toaday's date
    private String getTodaysDate() {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(today);
        return date;
    }

    private void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {

    }
}
