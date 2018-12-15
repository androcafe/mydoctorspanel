package androcafe.visitindia.com.mydoctorspanel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import visitindia.androcafe.doctorspanel.R;
import visitindia.androcafe.doctorspanel.model.CancelApmt;

public class CancelApmtAdapter extends BaseAdapter {

    Context context;
    List<CancelApmt> listCanceclApmt=new ArrayList<>();

    public CancelApmtAdapter(Context context, List<CancelApmt> listCanceclApmt) {
        this.context = context;
        this.listCanceclApmt = listCanceclApmt;
    }

    @Override
    public int getCount() {
        return listCanceclApmt.size();
    }

    @Override
    public Object getItem(int i) {
        return listCanceclApmt.get(i);
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
            view=layoutInflater.inflate(R.layout.list_cancel_apmt,null);
        }

        TextView tvPatientName=view.findViewById(R.id.tv_patient_name);
        TextView tvPatientAge=view.findViewById(R.id.tv_patient_age);
        TextView tvDate=view.findViewById(R.id.tv_apmt_date);
        TextView tvMedicalAlmt=view.findViewById(R.id.tv_patient_medical_alignment);

        Button btnCancelApmt=view.findViewById(R.id.btn_cancel_apmt);

        String patientName=tvPatientName.getText().toString();
        String patientAge=tvPatientAge.getText().toString();
        String apmtDate=tvDate.getText().toString();
        String medAlign=tvMedicalAlmt.getText().toString();

        btnCancelApmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return view;
    }
}
