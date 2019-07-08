package com.kevin.demo.base_of_cconcurrency;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:JoinAndSleepDemo
 * @Description: TODO
 */
public class JoinDemo {

   private static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("子线1程在执行----------1111111111");
        }
    });

   private static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("子线程2在执行----------2222222222");
        }
    });

    public static void main(String[] args) throws InterruptedException {
        //主线程
        System.out.println("主线程状态---------->"+Thread.currentThread().getState());
        System.out.println("---------主线程------"+Thread.currentThread().getName());
        System.out.println("主线程状态---------->"+Thread.currentThread().getState());
        System.out.println("子线程执行");
        //--------子线程执行
        thread1.start();
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());
        System.out.println("线程111111111状态-----------》"+thread1.getState());
        thread1.join();
        System.out.println("线程111111111状态-----------》"+thread1.getState());
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());
        thread2.start();
        System.out.println("线程222222222状态-----------》"+thread2.getState());
        thread2.join();
        System.out.println("线程222222222状态-----------》"+thread2.getState());
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());
        //主线程希望等待子线程执行完再执行
        System.out.println("主线程还在执行");
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());
    }
}
