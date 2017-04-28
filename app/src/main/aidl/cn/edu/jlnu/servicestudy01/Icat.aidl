// Icat.aidl
package cn.edu.jlnu.servicestudy01;
import cn.edu.jlnu.servicestudy01.Person;

// Declare any non-default types here with import statements

interface Icat {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setName(String name);
    String desc();
    Person getPerson();
}
