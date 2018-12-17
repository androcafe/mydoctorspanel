package androcafe.visitindia.com.mydoctorspanel.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import androcafe.visitindia.com.mydoctorspanel.R;


public class ChatActivity extends AppCompatActivity {

    RecyclerView rlChat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
}
