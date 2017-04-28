package cn.edu.jlnu.servicestudy01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 绑定服务：
 * 通过绑定服务来实现功能的步骤：
 * 1、客服端通过bindService方法来绑定一个服务对象，如果绑定成功，会调用onServiceConnected()方法
 * 2、通过Service组件来暴漏业务接口
 * 3、服务端通过创建*.aidl文件来定义一个可以被客户端调用的业务接口
 * 一个aidl文件：
 * (1)不能有修饰符，类似接口的写法
 * (2)支持类型:8种基本数据类型，String,CharSequence,List<String>,Map,自定义类型
 *
 * 自定义类型：
 * 实现Parceable接口
 * 定义一个aidl文件，声明该类型：parcelable Person
 * 在其他aidl文件中使用，必须要使用import
 *
 *
 * 4、服务端需要提供一个业务接口的实现类，通常我们会继承Stub类
 * 5、通过Service的onBind()返回被绑定的业务对象
 * 6、客户端如果绑定成功，就可以像调用自己的方法一样调用远程业务对象
 *
 */
public class MyBoundService extends Service {
    public MyBoundService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 解除绑定
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 绑定
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new CatImpl();
    }
}
