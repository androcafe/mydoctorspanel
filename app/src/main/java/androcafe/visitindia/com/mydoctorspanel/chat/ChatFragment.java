package androcafe.visitindia.com.mydoctorspanel.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.SignInActivity;

public class ChatFragment extends Fragment {

    //Declare all UI components
    ImageView ivSend;
    EditText edtSMS;
    TextView tvSMS;

    public static ChatFragment newInstance() {

        Bundle args = new Bundle();

        ChatFragment fragment = new ChatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_insert_chat,null);

        ivSend=view.findViewById(R.id.ivSend);
        edtSMS=view.findViewById(R.id.edt_msg);
        tvSMS=view.findViewById(R.id.tv_sms);

        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(view);
            }
        });

        tvSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatActivity.inserted_msg=edtSMS.getText().toString().trim();

                edtSMS.setText(" ");
                hideKeyboard(view);
            }
        });

        return view;
    }
    private void hideKeyboard(View view) {
        // Get the input method manager
        InputMethodManager inputMethodManager = (InputMethodManager)
                view.getContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        // Hide the soft keyboard
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
