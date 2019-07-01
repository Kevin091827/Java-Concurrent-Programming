package com.kevin.demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:AtomicIntegerArrayTest
 * @Description: TODO
 */
public class AtomicIntegerArrayTest {

    static int[] value = new int[]{1,2};

    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(value);

    public static void main(String[] args) {

        System.out.println("--------->"+atomicIntegerArray.addAndGet(0,2));
        System.out.println("--------->"+atomicIntegerArray.get(1));
        System.out.println("--------->"+atomicIntegerArray.getAndSet(1,2));
    }
}
