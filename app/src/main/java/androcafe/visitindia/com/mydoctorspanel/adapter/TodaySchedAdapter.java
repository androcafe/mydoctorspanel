package androcafe.visitindia.com.mydoctorspanel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.model.TodaysShedule;

public class TodaySchedAdapter extends BaseAdapter {

    Context context;
    List<TodaysShedule> todaysSheduleList=new ArrayList<>();

    public TodaySchedAdapter(Context context, List<TodaysShedule> todaysSheduleList) {
        this.context = context;
        this.todaysSheduleList = todaysSheduleList;
    }

    @Override
    public int getCount() {
        return todaysSheduleList.size();
    }

    @Override
    public Object getItem(int i) {
        return todaysSheduleList.get(i);
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
            view=layoutInflater.inflate(R.layout.list_todays_sched,null);
        }

        TextView tvPatientName=view.findViewById(R.id.tv_patient_name);
        TextView tvPatientAge=view.findViewById(R.id.tv_patient_age);
        TextView tvDate=view.findViewById(R.id.tv_apmt_date);
        TextView tvMedicalAlmt=view.findViewById(R.id.tv_patient_medical_alignment);


        tvPatientName.setText(todaysSheduleList.get(i).getPatient_name());
        tvMedicalAlmt.setText(todaysSheduleList.get(i).getTreatment());
        tvPatientAge.setText(todaysSheduleList.get(i).getPatient_age());
        tvDate.setText(todaysSheduleList.get(i).getApmt_date());


        return view;
    }
}
