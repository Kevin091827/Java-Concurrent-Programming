package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:    创建线程方式一 --- 继承Thread类重写run方法
 * @Author:         Kevin
 * @CreateDate:     2019/6/14 17:27
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/6/14 17:27
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
@Slf4j
public class NewThread1 extends Thread{

    @Override
    public void run() {
        while (true){
            log.info("-----> Thread1111111111111111");
        }
    }
}
