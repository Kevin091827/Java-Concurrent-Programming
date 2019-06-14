package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description:    创建线程的方式四 --- 基于线程池
 * @Author:         Kevin
 * @CreateDate:     2019/6/14 17:29
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/6/14 17:29
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
public class NewThead4 {

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 500,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));

        //执行任务
        while (true) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("------------>newThread444444444444444");
                }
            });
        }
    }
}
