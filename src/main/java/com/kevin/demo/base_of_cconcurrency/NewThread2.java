package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:NewThread2
 * @Description: TODO
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
