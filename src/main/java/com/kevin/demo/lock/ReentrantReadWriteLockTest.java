package com.kevin.demo.lock;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:ReentrantReadWriteLockTest
 * @Description: TODO
 */
public class ReentrantReadWriteLockTest {

    //通过读写锁来维护一个线程不安全的hashmap
    static HashMap<String,Object> hashMap = new HashMap<>();
    //获取读写锁
    static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    //获取读锁
    static Lock readLock = reentrantReadWriteLock.readLock();
    //获取写锁
    static Lock writeLock = reentrantReadWriteLock.writeLock();

    /**
     * 获取值  ----> 使用读锁，在同一时刻多个线程可以并发访问该方法，而不会被阻塞
     * @param key
     * @return
     */
    public static final Object getValue(String key){
        readLock.lock();
        try{
            return hashMap.get(key);
        }finally {
            readLock.unlock();
        }
    }

    /**
     * 写入map ------> 使用写锁，在同一时刻只有线程访问，只有当该线程释放锁，其他线程才可以访问
     * @param key
     * @param value
     */
    public static final void putValue(String key,Object value){
        writeLock.lock();
        try{
            hashMap.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }

    static class ReadWriteLockTest implements ReadWriteLock{

        @Override
        public Lock readLock() {
            return null;
        }

        @Override
        public Lock writeLock() {
            return null;
        }
    }
}
