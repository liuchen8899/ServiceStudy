package cn.edu.jlnu.servicestudy01;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Icat mIcat;
    private boolean mBound=false; //是否绑定

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService(View v){
        Intent intent=new Intent(this,MyService.class);
        intent.putExtra("info","hello world!");
        startService(intent);
    }

    public void stopService(View v){
        Intent intent=new Intent(this,MyService.class);
        stopService(intent);
    }

    public void startIntentService(View v){
        Log.e("liuchen","startIntentService;onclick");
        Intent intent=new Intent(MainActivity.this,MyIntentService.class);
        intent.putExtra("info","hello liuchen8899! you are clever!");
        startService(intent);
    }

    public void stopIntentService(View v){
        Log.e("liuchen","startIntentService;onclick");
        Intent intent=new Intent(MainActivity.this,MyIntentService.class);
        stopService(intent);
    }


    /***
     * 绑定服务的连接回调接口
     */
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            //绑定成功后回调的方法
            Log.e("liuchen","绑定成功！");
            mIcat=Icat.Stub.asInterface(binder);
            Log.e("liuchen",mIcat+"");
            mBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("liuchen","绑定失败！");
            //服务异常时调用
            mBound=false;
        }
    };

    /**
     * 绑定服务
     * @param v
     */
    public void boundService(View v){
        Intent intent=new Intent(this,MyBoundService.class);
        //异步绑定,绑定成功后会回调onServiceConnected
        bindService(intent,conn, Context.BIND_AUTO_CREATE); //自动创建
        return;
    }

    /**
     * 解除绑定
     * @param v
     */
    public void unBoundService(View v){
       if(mBound){
           Log.e("liuchen","解除绑定成功！");
           unbindService(conn);
       }
    }

    public void call(View v){
        if(mIcat==null){
            return;
        }
        try {
            mIcat.setName("喵喵");
            Log.e("liuchen",mIcat.desc());
            Log.e("liuchen",this.mIcat.getPerson().toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    Messenger mService;
    boolean flag=false;

    private ServiceConnection conn1=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mService=new Messenger(binder);
            flag=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            flag=false;
            mService=null;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,MessengerService.class);
        bindService(intent,conn1,Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(flag){
            unbindService(conn1);
            flag=false;
        }

    }

    //使用messenger
    public void messenger(View v){
        //获取一个消息对象
        Message msg=Message.obtain();
        msg.what= MessengerService.say_hello;
        msg.obj="你好，刘晨8899！";
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
