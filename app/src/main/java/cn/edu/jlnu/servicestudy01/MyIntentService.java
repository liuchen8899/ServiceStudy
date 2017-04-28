package cn.edu.jlnu.servicestudy01;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.view.View;


/***
 * intent service
 * 1.内部有一个工作线程来完成耗时操作，只需要实现onHandleIntent方法即可
 */
public class MyIntentService extends IntentService {

    private  final String TAG ="liuchen" ;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"MyIntentService onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"MyIntentService onDestroy");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
      //  Log.e(TAG,intent.getStringExtra("info"));
        for(int i=0;i<50;i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG,"onHandleIntent-"+i+"-----"+Thread.currentThread().getName());
        }
    }




}
