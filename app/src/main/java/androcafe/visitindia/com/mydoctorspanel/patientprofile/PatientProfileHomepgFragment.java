package androcafe.visitindia.com.mydoctorspanel.patientprofile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androcafe.visitindia.com.mydoctorspanel.DoctorPanelUrl;
import androcafe.visitindia.com.mydoctorspanel.R;

public class PatientProfileHomepgFragment extends Fragment implements DoctorPanelUrl {

    //Declare all widgets and UI components
    FloatingActionButton fbPhoneCall,fbSMS;

    public static PatientProfileHomepgFragment newInstance() {

        Bundle args = new Bundle();

        PatientProfileHomepgFragment fragment = new PatientProfileHomepgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_patient_profle_homepg,null);

        //Load Paient Data from server
        loadPatientProfileData();

        //Initialization of UI components
        fbPhoneCall=view.findViewById(R.id.fb_call);
        fbSMS=view.findViewById(R.id.fb_sms);

        //onClickListener to phone floating buttong
        fbPhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Call to patient
                Intent intentCall=new Intent(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("tel:9922004455"));
                startActivity(intentCall);
            }
        });
        return view;
    }

    private void loadPatientProfileData() {
        //create new requestqueue
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());

        JsonObjectRequest jsonObject=new JsonObjectRequest(Request.Method.POST, ALL_APMT_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray=response.getJSONArray("appointment");

                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id = jsonObject.getInt("appointmentid");
                        int pid = jsonObject.getInt("patient_id");
                        String name = jsonObject.getString("name");
                        String age = jsonObject.getString("patient_age");
                        String treatment = jsonObject.getString("patient_treatment");
                        String date_of_appointment = jsonObject.getString("date_of_appointment");
                        String time = jsonObject.getString("time");
                        String sex = jsonObject.getString("sex");
                        String doc_id = jsonObject.getString("doctor_id");
                        String created_at = jsonObject.getString("created_at");

                        System.out.println(" " + doc_id);

//                        if(doc_id.equals(doctor_id))
//                        {
//                            apmtStatus=new ApmtStatus(id,pid,name,age,sex,date_of_appointment,time,created_at,treatment,doc_id);
//                            listApmt.add(apmtStatus);
//                        }
//                    }
//                    myApmtStatusAdapter=new MyApmtStatusAdapter(getActivity(),listApmt);
//                    lvApmtStatus.setAdapter(myApmtStatusAdapter);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Error : ", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObject);

    }
}
