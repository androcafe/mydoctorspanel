package androcafe.visitindia.com.mydoctorspanel.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ChatService extends Service {

    private IBinder myBinder = new MyBinder();

    //1. onCreate
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //2. onBind
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    //Innner Class
    public class MyBinder extends Binder {
        public ChatService getService() {
            return ChatService.this;
        }
    }

}
