package cn.edu.jlnu.servicestudy01;

import android.os.RemoteException;

/**
 * 业务接口的具体实现
 * Created by Administrator on 2017/4/27.
 */

public class CatImpl extends Icat.Stub {

    private String name;

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name=name;
    }

    @Override
    public String desc() throws RemoteException {
        return "你好，我的名字是："+name;
    }

    @Override
    public Person getPerson() throws RemoteException {
        Person person=new Person();
        person.name="刘晨";
        person.work="本科生";
        return person;
    }
}
