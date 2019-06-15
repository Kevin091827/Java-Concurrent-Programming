package com.kevin.demo.executor;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:ExecutorTest
 * @Description: TODO
 */
@Slf4j
public class ExecutorTest {

    /**
     * callable测试内部类
     */
    static class CallableTest implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            return 1;
        }
    }

    /**
     * runnable测试内部类
     */
    static class RunnableTest implements Runnable{
        @Override
        public void run() {
            log.info("runnable ........");
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构造线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                100, MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

        //提交并执行runnable任务
        threadPoolExecutor.execute(new RunnableTest());

        //提交并执行callable任务，获取返回的Future对象
        Future<Integer> future = threadPoolExecutor.submit(new CallableTest());
        //通过该对象获取返回值
        Integer i = future.get();
        log.info("i ---------->"+i);
    }
}
