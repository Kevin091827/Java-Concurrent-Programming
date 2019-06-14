package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:    创建线程的方式二 --- 实现Runnable接口重写run方法
 * @Author:         Kevin
 * @CreateDate:     2019/6/14 17:28
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/6/14 17:28
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
public class NewThread2 implements Runnable{

    @Override
    public void run() {
        while(true){
            log.info("---------> newThread222222222222222");
        }
    }
}
