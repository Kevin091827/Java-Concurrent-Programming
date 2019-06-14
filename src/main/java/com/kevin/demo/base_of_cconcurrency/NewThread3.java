package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Description:    创建线程的方式三 --- 实现Callable接口，重写call方法
 * @Author:         Kevin
 * @CreateDate:     2019/6/14 17:28
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/6/14 17:28
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
public class NewThread3 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int i = 0;
        while(i<10000){
            log.info("--------> newThread333333333333");
            i++;
        }
        return i;
    }
}
