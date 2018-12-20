package androcafe.visitindia.com.mydoctorspanel.profilefragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androcafe.visitindia.com.mydoctorspanel.OnBackPressed;
import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.SignInActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class EducationFragment extends Fragment implements OnBackPressed {

    Spinner spinnerEdu;
    CheckBox cbBachelor,chMaster,chPhd;
    CircleImageView imageView;
    TextView tvQualification;

    ArrayAdapter<String> arrayAdapter;

    Button btnSave;

    String specialize="";

    SharedPreferences sharedPreferences;

    public static EducationFragment newInstance() {

        Bundle args = new Bundle();

        EducationFragment fragment = new EducationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.profile_education,null);

        sharedPreferences=getActivity().getSharedPreferences(SignInActivity.MYDOCPREF, Context.MODE_PRIVATE);
        String photo=sharedPreferences.getString("PHOTO",null);

        imageView=view.findViewById(R.id.iv_doc_profile);

        /*byte [] encodeByte= Base64.decode(photo,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);*/
       // imageView.setImageBitmap(bitmap);

        spinnerEdu=view.findViewById(R.id.spinnerEducation);

        tvQualification=view.findViewById(R.id.tv_edu);

        //Initialize checkbox
        cbBachelor=view.findViewById(R.id.checkbox_bachelor);
        chMaster=view.findViewById(R.id.checkbox_master);
        chPhd=view.findViewById(R.id.checkbox_phd);

        btnSave=view.findViewById(R.id.btn_save);

        final String edu[]=getResources().getStringArray(R.array.array_edu);

        arrayAdapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,edu);
        spinnerEdu.setAdapter(arrayAdapter);

        spinnerEdu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(edu[i].equals("Specialization"))
                {
                    //If nothing is choose rather than Specialization
                }
                else
                {
                    specialize=edu[i];

                    tvQualification.setVisibility(View.VISIBLE);

                    //Set checkbox visibility visible
                    cbBachelor.setVisibility(View.VISIBLE);
                    chMaster.setVisibility(View.VISIBLE);
                    chPhd.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                String Education="";

                if(specialize.equals(""))
                {
                    flag=1;
                    Toast.makeText(getActivity(),"Select your specialization", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //Check education Qualification
                    if(chPhd.isChecked())
                    {
                        Education="Phd";
                    }
                    else if(chMaster.isChecked())
                    {
                        Education="Master";
                    }
                    else if(cbBachelor.isChecked())
                    {
                        Education="Bachelor";
                    }
                    else
                    {
                        flag=1;
                        Toast.makeText(getActivity(),"Select your degree", Toast.LENGTH_LONG).show();
                    }
                }

                if(flag==0)
                {
                    saveDataToSharedPref(specialize,Education);

                    Fragment fragmentEduFragment=CertificateFragment.newInstance();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.framelayout_profile,fragmentEduFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }

    private void saveDataToSharedPref(String specialize, String education) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("SPECIALIZE",specialize);
        editor.putString("EDUCATION",education);
        editor.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
