package com.kevin.demo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description:    独占锁
 * @Author:         Kevin
 * @CreateDate:     2019/6/19 23:51
 * @UpdateUser:     Kevin
 * @UpdateDate:     2019/6/19 23:51
 * @UpdateRemark:   修改内容
 * @Version: 1.0
 */
public class Mutex implements Lock {

    /**
     * 同步器
     */
    private static class Sync extends AbstractQueuedSynchronizer{
        /**
         * 获取锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         * @param arg
         * @return
         */
        @Override
        protected boolean tryRelease(int arg) {
           if(getState() == 0){
               try {
                   throw new Exception();
               } catch (Exception e) {
                   e.printStackTrace();
               }
               return false;
           }else {
               setExclusiveOwnerThread(null);
               setState(0);
               return true;
           }
        }

        /**
         * 是否处于占用状态
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
       return (Condition) sync.getExclusiveQueuedThreads();
    }
}
