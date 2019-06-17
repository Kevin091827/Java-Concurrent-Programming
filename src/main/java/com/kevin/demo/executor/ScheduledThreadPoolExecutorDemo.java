package com.kevin.demo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:ScheduledThreadPoolExecutorDemo
 * @Description: TODO
 */
public class ScheduledThreadPoolExecutorDemo {

    /**
     * 实现ScheduledThreadPoolExecutor
     * @return
     */
    public ExecutorService scheduledThreadPoolExecutor(){
        return Executors.newScheduledThreadPool(8);
    }
}
