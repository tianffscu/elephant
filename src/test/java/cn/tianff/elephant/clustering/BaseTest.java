package cn.tianff.elephant.clustering;

import org.junit.Test;

public class BaseTest {


    @Test
    public void testReflect(){

        String ss = "Hello";
        Class clazz = ss.getClass();

        System.out.println(clazz.getSimpleName());


    }
}
