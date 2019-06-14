package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @Description:    获取线程信息
 * @Author:         Kevin
 * @CreateDate:     2019/6/14 17:29
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/6/14 17:29
 * @UpdateRemark:   修改内容
 * @Version: 1.0
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
