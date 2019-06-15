package com.kevin.demo.executor;

import org.apache.catalina.Executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:ThreadPoolExecutorDemo
 * @Description: TODO
 */
public class ThreadPoolExecutorDemo {

    /**
     * @Param: corePoolSize : 核心线程数
     * @Param：maximumPoolSize：最大线程数
     * @Param：keepAliveTime：空闲线程等待新任务的最长时间
     * @Param：unit：计数单位
     * @Param：workQueue：任务队列
     */
    //ThreadPoolExecutor tpe = new ThreadPoolExecutor(int corePoolSize,int maximumPoolSize
    //                              ,long keepAliveTime,TimeUnit unit, BlockingQueue<Runnable> workQueue);


    /**
     * 手动创建线程池
     * @return
     */
    public ExecutorService executorService(){
        return new ThreadPoolExecutor(5, 10,
                100, MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
    }

    /**
     * 创建 FixedThreadPool
     * @return
     */
    public ExecutorService fixedThreadPool(){
        return Executors.newFixedThreadPool(8);
    }

    /**
     * 创建SingleThreadExecutor
     * @return
     */
    public ExecutorService singleThreadExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    /**
     * 创建CachedThreadPool
     * @return
     */
    public ExecutorService cachedThreadPool(){
        return Executors.newCachedThreadPool();
    }
}
