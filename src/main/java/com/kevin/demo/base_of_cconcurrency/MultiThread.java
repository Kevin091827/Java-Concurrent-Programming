package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:MultiThread
 * @Description: TODO
 */
@Slf4j
public class MultiThread {

    public static void main(String[] args) {
        // 获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 获取线程和线程的堆栈信息（不需要获取线程的Monitors，synchronizers）
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        // 打印线程信息
        for(ThreadInfo threadInfo:threadInfos){
            log.info("threadName:--------->"+threadInfo.getThreadName());
        }
    }
}
