package com.kevin.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:ConditionDemo
 * @Description: TODO
 */
public class ConditionDemo {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 线程等待（对中断敏感）
     * <h> condition.await() </h>
     * <p>
     *    等待：
     *          当前线程进入等待状态直到被通知或被中断
     *    唤醒：
     *          1.signal()，signalAll()
     *          2.其他线程调用interrupt()中断该线程（对中断敏感）
     * </p>
     */
    public void awaitThread(){
        //执行condition相关方法先获取到锁（lock）
        lock.lock();
        try{
            //执行condition相关方法
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }
    }

    /**
     * 线程等待（对中断不敏感）
     * <h> condition.awaitUninterruptibly() </h>
     * <p>
     *    等待：
     *          当前线程进入等待状态直到被通知
     *    唤醒：
     *          1.signal()，signalAll()
     * </p>
     */
    public void awaitUninterruptiblyThread(){
        //执行condition相关方法先获取到锁（lock）
        lock.lock();
        try{
            //执行condition相关方法
            condition.awaitUninterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放锁
            lock.unlock();
        }
    }


    /**
     * 唤醒当前等待的线程
     * <h> condition.signal() </h>
     */
    public void signalThread(){
        lock.lock();
        try {
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    /**
     * 唤醒所有等待的线程
     * <h> condition.signalAll() </h>
     */
    public void signalAllThread(){
        lock.lock();
        try {
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
