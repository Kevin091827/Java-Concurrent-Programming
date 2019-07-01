package com.kevin.demo.atomic;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static sun.net.InetAddressCachePolicy.get;

/**
 * @Description:    原子更新基本类型
 * @Author:         Kevin
 * @CreateDate:     2019/7/1 21:34
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/7/1 21:34
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class AtomicIntergerTest {

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        //原子的方式增加指定的值
        System.out.println("--------->" + atomicInteger.addAndGet(1));
        //compareAndSet(int expect,int update)如果expect = atomicInteger.get() 则将该atomicInteger.get()设置为update
        System.out.println("--------->"+atomicInteger.compareAndSet(1,2)+atomicInteger.get());
        //自增1，返回的是自增前的值
        System.out.println("--------->"+atomicInteger.getAndIncrement());
        //自减1，返回的是自减前的值
        System.out.println("--------->"+atomicInteger.getAndDecrement());
        //延时更新
        atomicInteger.lazySet(1);
    }

    /**
     * getAndIncrement()原理
     * @return
     */
    public static int getAndIncrement(){
        for(;;){
            int current = get();
            int next = current+1;
            if(atomicInteger.compareAndSet(current,next)){
                return current;
            }
        }
    }
}
