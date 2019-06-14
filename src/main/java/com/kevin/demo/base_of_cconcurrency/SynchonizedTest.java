package com.kevin.demo.base_of_cconcurrency;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:SynchonizedTest
 * @Description: TODO
 */
public class SynchonizedTest {

    private int i = 0;

    private static int j = 0;

    /**
     * synchronized修饰实例方法   内置锁是当前类对象
     * @return
     */
    public synchronized int test1(){
        return i++;
    }

    /**
     * synchronized修饰静态方法   内置锁是当前类的class对象
     * @return
     */
    public synchronized  static int test2(){
        return j++;
    }

    /**
     * synchronized修饰同步代码块  内置锁是自定义的对象
     */
    public void test3(){
        synchronized (Integer.valueOf(i)){
            i++;
        }
    }
}
