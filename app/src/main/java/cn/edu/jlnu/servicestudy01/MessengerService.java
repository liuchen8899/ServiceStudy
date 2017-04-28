package cn.edu.jlnu.servicestudy01;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {

    public static final int say_hello=0x1;

    public MessengerService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case say_hello:
                   String info= (String) msg.obj;
                   Toast.makeText(getApplicationContext(),info,Toast.LENGTH_SHORT).show();
                   break;
           }
        }
    };

    private Messenger messenger=new Messenger(mHandler);
}
