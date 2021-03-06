package androcafe.visitindia.com.mydoctorspanel.chat;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androcafe.visitindia.com.mydoctorspanel.R;
import androcafe.visitindia.com.mydoctorspanel.patientprofile.PatientProfileActivity;
import androcafe.visitindia.com.mydoctorspanel.services.ChatService;


public class ChatActivity extends AppCompatActivity {


    //Initialize UI components
    RecyclerView rlChat;
    FrameLayout frameLayout_chat;

    //Fragment for chat
    Fragment fragment;

    //inserted msg stored into static variable
    public static String inserted_msg="";

    //Chat Service
    ChatService chatBoundService;
    boolean chatServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setTitle(getResources().getString(R.string.string_chat));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize UI components
        rlChat=findViewById(R.id.rl_chat);
        frameLayout_chat=findViewById(R.id.frame_chat);


        //Initialize linear layout
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(ChatActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //Initialize Fragment
        fragment=ChatFragment.newInstance() ;
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        if(fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_chat,fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(ChatActivity.this,PatientProfileActivity.class);
                startActivity(intent);
                return true;
        }
        return  false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ChatActivity.this,PatientProfileActivity.class);
        finish();
        startActivity(intent);
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            chatServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ChatService.MyBinder myBinder = (ChatService.MyBinder) service;
            chatBoundService = myBinder.getService();
            chatServiceBound = true;
        }
    };



}
