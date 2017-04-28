package cn.edu.jlnu.servicestudy01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * 服务只会被创建一次，可以通过外部调用stopService或调用stopSelf来终止服务
 * 当启动一个已启动的服务，会直接调用onStartCommand方法来执行
 * 默认情况下，服务与主线程在同一个进程中的同一个线程中执行，如果服务执行一个比较耗时的1操作，我们必须使用子线程来完成工作
 * 避免阻塞主线程
 * 使用started service启动的一个服务，没有关闭之前会一直在后台独立运行
 */
public class MyService extends Service {
    private static String TAG="liuchen";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"MyService onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"MyService onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /**
         * 使用线程完成长时间的工作
         */
        Log.e(TAG,intent.getStringExtra("info"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<50;i++){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG,"i="+i+"----------"+Thread.currentThread().getName());
                }
                MyService.this.stopSelf();
            }

        }).start();

        return super.onStartCommand(intent, flags, startId);
    }
}
