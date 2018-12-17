package androcafe.visitindia.com.mydoctorspanel.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

import androcafe.visitindia.com.mydoctorspanel.DoctorPanelUrl;
import androcafe.visitindia.com.mydoctorspanel.HomeActivity;
import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.SignInActivity;
import androcafe.visitindia.com.mydoctorspanel.adapter.MyApmtStatusAdapter;
import androcafe.visitindia.com.mydoctorspanel.model.ApmtStatus;

import static android.content.Context.MODE_PRIVATE;

public class AppointmentStatusFragment extends Fragment implements DoctorPanelUrl {


    //Listview in ApmtStatusFragment
    ListView lvApmtStatus;

    //Model class for ApmtStatus
    ApmtStatus apmtStatus;

    //List of type ApmtStatus to hold the data
    List<ApmtStatus> listApmt=new ArrayList<>();

    //CustomAdapter of an apmt
    MyApmtStatusAdapter myApmtStatusAdapter;

    //Pref object
    SharedPreferences sharedPreferences;

    String doctor_id;

    //create instance of a fragment
    public static AppointmentStatusFragment newInstance() {

        Bundle args = new Bundle();

        AppointmentStatusFragment fragment = new AppointmentStatusFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_apmt_status,null);

        //Sharedpreference Initialization
        sharedPreferences= getActivity().getSharedPreferences(SignInActivity.MYDOCPREF, MODE_PRIVATE);

        doctor_id=sharedPreferences.getString(SignInActivity.DID,null);

        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("Appointment Status");

        finViewByIds(view);

        getAllApmtData();

        return view;
    }

    private void finViewByIds(View view) {

        lvApmtStatus=view.findViewById(R.id.listview_apmt_status);
    }

    private void getAllApmtData() {

        //create new requestqueue
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());


        JsonObjectRequest jsonObject=new JsonObjectRequest(Request.Method.POST, ALL_APMT_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray=response.getJSONArray("appointment");

                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id=jsonObject.getInt("appointmentid");
                        int pid=jsonObject.getInt("patient_id");
                        String name=jsonObject.getString("name");
                        String age=jsonObject.getString("patient_age");
                        String treatment=jsonObject.getString("patient_treatment");
                        String date_of_appointment=jsonObject.getString("date_of_appointment");
                        String time=jsonObject.getString("time");
                        String sex=jsonObject.getString("sex");
                        String doc_id=jsonObject.getString("doctor_id");
                        String created_at=jsonObject.getString("created_at");

                        System.out.println(" "+doc_id);

                        if(doc_id.equals(doctor_id))
                        {
                            apmtStatus=new ApmtStatus(id,pid,name,age,sex,date_of_appointment,time,created_at,treatment,doc_id);
                            listApmt.add(apmtStatus);
                        }
                    }
                     myApmtStatusAdapter=new MyApmtStatusAdapter(getActivity(),listApmt);
                     lvApmtStatus.setAdapter(myApmtStatusAdapter);
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
