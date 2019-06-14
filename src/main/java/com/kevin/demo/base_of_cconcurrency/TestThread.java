package com.kevin.demo.base_of_cconcurrency;

import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:TestThread
 * @Description: TODO
 */
@Slf4j
public class TestThread {

    // volatile关键字保证线程对该变量访问的可见性
    volatile boolean isOn;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程构建方式一
        NewThread1 newThread1 = new NewThread1();
        newThread1.start();

        // 线程构建方式二
        Thread thread = new Thread(new NewThread2());
        thread.start();

        // 线程构建方式三
        FutureTask futureTask = new FutureTask(new NewThread3());
        Thread thread3 = new Thread(futureTask);
        thread3.start();

        //获取返回值
        int i = (int) futureTask.get();
        log.info("-------> "+i);

        //同步代码块
        synchronized (TestThread.class){

        }

    }

    //同步方法
    public synchronized void test(){

    }

}
