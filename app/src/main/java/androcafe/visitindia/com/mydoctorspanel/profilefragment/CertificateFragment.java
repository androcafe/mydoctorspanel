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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import visitindia.androcafe.doctorspanel.OnBackPressed;
import visitindia.androcafe.doctorspanel.R;
import visitindia.androcafe.doctorspanel.SignInActivity;

public class CertificateFragment extends Fragment implements OnBackPressed {

    EditText edtCertificate;

    Button btnSave;

    RelativeLayout rlCert1;
    LinearLayout llCert1,llCert2;

    SharedPreferences sharedPreferences;

    String certificate="";

    public static CertificateFragment newInstance() {

        Bundle args = new Bundle();

        CertificateFragment fragment = new CertificateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.profile_certificate,null);

        sharedPreferences=getActivity().getSharedPreferences(SignInActivity.MYDOCPREF, Context.MODE_PRIVATE);

        llCert1=view.findViewById(R.id.ll_cert1);
        llCert2=view.findViewById(R.id.ll_cert2);
        rlCert1=view.findViewById(R.id.rl_cert);

        llCert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        llCert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        rlCert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
            }
        });

        edtCertificate=view.findViewById(R.id.edt_certificate);

        btnSave=view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                certificate=edtCertificate.getText().toString();

                hideKeyboard(view);

                saveDataToSharedPref(certificate);
                Fragment fragmentCertFragment=BiographyFragment.newInstance();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout_profile,fragmentCertFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    private void saveDataToSharedPref(String certificate) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("CERTIFICATE",certificate);
        editor.commit();
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
