package androcafe.visitindia.com.mydoctorspanel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.model.ApmtStatus;
import androcafe.visitindia.com.mydoctorspanel.patientprofile.PatientProfileActivity;


public class MyApmtStatusAdapter extends BaseAdapter {

    Context context;
    List<ApmtStatus> list;


    public MyApmtStatusAdapter(FragmentActivity activity, List<ApmtStatus> listApmt) {
        context=activity;
        this.list=listApmt;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view==null)
        {
            LayoutInflater layoutInflater= LayoutInflater.from(context);
            view=layoutInflater.inflate(R.layout.list_apmt_status,null);
        }

        RelativeLayout rlApmtStatus=view.findViewById(R.id.rl_apmt_status);
        TextView tvName=view.findViewById(R.id.tv_patient_name);
        TextView tvDate=view.findViewById(R.id.tv_apmt_date);
        TextView tvTime=view.findViewById(R.id.tv_apmt_time);
        TextView tvMedicalAlignment=view.findViewById(R.id.tv_patient_medical_alignment);

        rlApmtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPatientProfile=new Intent(context, PatientProfileActivity.class);
                context.startActivity(intentPatientProfile);
            }
        });

        tvName.setText(""+list.get(i).getName());
        tvDate.setText(""+list.get(i).getDate_of_apmt());
        tvTime.setText(""+list.get(i).getTime());
        tvMedicalAlignment.setText(""+list.get(i).getTreatment());

        return view;
    }

}
