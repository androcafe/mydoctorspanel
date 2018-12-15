package androcafe.visitindia.com.mydoctorspanel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import visitindia.androcafe.doctorspanel.R;
import visitindia.androcafe.doctorspanel.cancelappoint.CancelApmtActivity;
import visitindia.androcafe.doctorspanel.changesched.ChangeScheduleActivity;
import visitindia.androcafe.doctorspanel.model.Home;

public class HomeGridAdapter extends BaseAdapter {
    Context context;
    List<Home> homeList;


    public HomeGridAdapter(FragmentActivity activity, List<Home> listHome) {
        this.context=activity;
        this.homeList=listHome;
    }

    @Override
    public int getCount() {
        return homeList.size();
    }

    @Override
    public Object getItem(int i) {
        return homeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            LayoutInflater inflater= LayoutInflater.from(context);
            view=inflater.inflate(R.layout.grid_row_home,null);
        }

        LinearLayout linearLayout=view.findViewById(R.id.linearlayout_change_schedule);
        ImageView imgHome=view.findViewById(R.id.imageview_home);
        final TextView tvHome=view.findViewById(R.id.tv_home);

        imgHome.setImageResource(homeList.get(i).getImg());
        tvHome.setText(homeList.get(i).getName());

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvHome.getText().toString().equals(context.getResources().getString(R.string.string_change_scedule)))
                {
                    Intent intentChangeSced=new Intent(context,ChangeScheduleActivity.class);
                    context.startActivity(intentChangeSced);

                }
                if(tvHome.getText().toString().equals(context.getResources().getString(R.string.string_cancel_apmt)))
                {
                    Intent intentCancelApmt=new Intent(context,CancelApmtActivity.class);
                    context.startActivity(intentCancelApmt);
                }



            }
        });

        return view;
    }
}
