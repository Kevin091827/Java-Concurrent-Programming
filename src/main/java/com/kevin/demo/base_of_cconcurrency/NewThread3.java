package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:NewThread3
 * @Description: TODO
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
