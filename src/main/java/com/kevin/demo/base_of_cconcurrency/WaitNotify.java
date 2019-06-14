package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:    等待超时机制 和 通知等待机制
 * @Author:         Kevin
 * @CreateDate:     2019/6/14 17:29
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/6/14 17:29
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    static class WaitThread implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                while(flag){
                    log.info("flag -------> true");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.info("flag -------------> false in NotifyThread after");
            }
        }
    }

    static class NotifyThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                log.info("flag -------------> true in Notify before");
                lock.notify();
                flag = false;
                log.info("flag ---------> false");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (lock){
                log.info("-------------------------");
            }
        }
    }

    /**
     * 超时等待机制
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public synchronized Object get(long mills) throws InterruptedException {

        Object result = new Object();
        long future = System.currentTimeMillis() + mills;
        long remaining = mills;
        //当超时大于0并且result返回值不满足条件
        while ((result == null) && remaining > 0) {
            wait(remaining);
            remaining = future - System.currentTimeMillis();
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new WaitThread());
        thread1.start();

        Thread.sleep(2000);

        Thread thread = new Thread(new NotifyThread());
        thread.start();
    }
}
