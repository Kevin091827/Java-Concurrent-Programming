package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:SleepDemo
 * @Description: TODO
 */
@Slf4j
public class SleepDemo {

    static class ThreadA extends Thread{

        @Override
        public void run() {
            synchronized (this){
                log.info("------>"+this.getName());
                log.info("------>"+Thread.currentThread().getName()+"begin:"+System.currentTimeMillis());

                log.info("------>"+Thread.currentThread().getName()+"end:"+System.currentTimeMillis());
            }
        }
    }

    static class ThreadB extends Thread{

        private ThreadA threadA;

        public ThreadB(ThreadA threadA) {
            this.threadA = threadA;
        }

        @Override
        public void run() {
            synchronized (threadA){
                log.info("------>"+Thread.currentThread().getName()+"begin:"+System.currentTimeMillis());
                try {
                    log.info("-------->before: "+threadA.isAlive());
                    threadA.join();
                    log.info("---------->after: "+threadA.isAlive());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("------>"+Thread.currentThread().getName()+"end:"+System.currentTimeMillis());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadA threadA = new ThreadA();
        threadA.setName("AAAAAAAAAAAA");
        ThreadB threadB = new ThreadB(threadA);
        threadB.setName("BBBBBBBBBBBB");

        threadB.start();
        threadA.start();
    }
}
