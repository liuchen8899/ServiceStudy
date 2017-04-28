package cn.edu.jlnu.servicestudy01;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/4/27.
 */

public class Person implements Parcelable{
    String name;
    String work;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", work='" + work + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public  static  final Parcelable.Creator<Person> CREATOR=new Parcelable.Creator<Person>(){

        @Override
        public Person createFromParcel(Parcel parcel) {
            Person p=new Person();
            p.name=parcel.readString();
            p.work=parcel.readString();
            return p;
        }

        @Override
        public Person[] newArray(int i) {
            return new Person[0];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flag) {
        dest.writeString(name);
        dest.writeString(work);
    }
}
