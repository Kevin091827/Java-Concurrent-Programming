package com.kevin.demo.base_of_cconcurrency;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:NewThread1
 * @Description: TODO
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
