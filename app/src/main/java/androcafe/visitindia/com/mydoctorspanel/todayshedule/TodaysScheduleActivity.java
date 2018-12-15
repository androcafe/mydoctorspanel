package androcafe.visitindia.com.mydoctorspanel.todayshedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import visitindia.androcafe.doctorspanel.DoctorPanelUrl;
import visitindia.androcafe.doctorspanel.HomeActivity;
import visitindia.androcafe.doctorspanel.R;
import visitindia.androcafe.doctorspanel.adapter.TodaySchedAdapter;
import visitindia.androcafe.doctorspanel.model.TodaysShedule;

public class TodaysScheduleActivity extends AppCompatActivity implements DoctorPanelUrl{

    //Listview to view all todays appointment
    ListView lv_todays_apmt;

    //Model class to hold data
    TodaysShedule todaysShedule;

    //List of type TodaySched
    List<TodaysShedule> todaysSheduleList=new ArrayList<>();

    //Custom Adapter for list
    TodaySchedAdapter todaySchedAdapter;

    //TextView to show no apmt message
    TextView tvNoApmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays_schedule);

        //set home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set Action bar title
        getSupportActionBar().setTitle("Today's Appointment");

        //Initialize all widgets and UI components
        findViewByIds();

        //get appointment details from server database
        getAllTodaysApmt();

    }

    //INitialize all widgets and UI Components
    private void findViewByIds() {

        //Initialize listview
        lv_todays_apmt=findViewById(R.id.lv_todays_apmt);

        //Initialize Textview
        tvNoApmt=findViewById(R.id.tv_no_apmt);

    }

    private void getAllTodaysApmt() {

        //create new requestqueue
        RequestQueue requestQueue= Volley.newRequestQueue(TodaysScheduleActivity.this);

        JsonObjectRequest jsonObject=new JsonObjectRequest(Request.Method.POST, ALL_APMT_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray jsonArray=response.getJSONArray("appointment");

                    todaysSheduleList.clear();
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        //get appointment details from JSONArray using key
                        int id=jsonObject.getInt("appointmentid");
                        int pid=jsonObject.getInt("patient_id");
                        String name=jsonObject.getString("name");
                        int age=jsonObject.getInt("patient_age");
                        String treatment=jsonObject.getString("patient_treatment");
                        String date_of_appointment=jsonObject.getString("date_of_appointment");
                        String time=jsonObject.getString("time");
                        String sex=jsonObject.getString("sex");
                        String doc_id=jsonObject.getString("doctor_id");
                        String created_at=jsonObject.getString("created_at");

                        //get toaday's date
                        String today=getTodaysDate();
                        System.out.println("today "+today);

                        //compare today's date with appointment list
                        if(date_of_appointment.equals(today))
                        {
                            todaysShedule=new TodaysShedule(id,pid,name,age,sex,date_of_appointment,time,created_at,treatment,doc_id);
                            todaysSheduleList.add(todaysShedule);
                        }

                    }

                    if(todaysSheduleList.size()>0)
                    {
                        //If there is appointment booked today
                        todaySchedAdapter=new TodaySchedAdapter(TodaysScheduleActivity.this,todaysSheduleList);
                        lv_todays_apmt.setAdapter(todaySchedAdapter);
                    }
                    else
                    {
                        System.out.println("dcdkjcd");
                        //if there is no appointment booked for today
                        tvNoApmt.setText(getResources().getString(R.string.string_no_apmt));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TodaysScheduleActivity.this,"Error : "+error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //add request to request queue
        requestQueue.add(jsonObject);
    }

    //to get toaday's date
    private String getTodaysDate() {
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(today);
        return date;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            //if home button is clicked
            case android.R.id.home:
                Intent intent=new Intent(TodaysScheduleActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                return true;

        }
        return  false;
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(TodaysScheduleActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
