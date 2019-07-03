package com.kevin.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:BoundedQueue
 * @Description: TODO
 */
public class BoundedQueue {

    //基于数组的简单有界队列
    private Integer[] array ;

    /**
     * <params>
     *     addIndex : 添加索引
     *     removeIndex ： 移除索引
     *     count ：当前队列元素数量
     * </params>
     */
    private int addIndex,removeIndex,count;

    private Lock lock = new ReentrantLock();

    //当队列满时的等待/通知机制
    private Condition fullCondition = lock.newCondition();

    //当队列空时的等待/通知机制
    private Condition emptyCondition = lock.newCondition();

    public BoundedQueue(int size){
        array = new Integer[size];
    }

    /**
     * 向队列添加一个元素，当队列满时阻塞线程，不能添加
     * @param i
     * @throws InterruptedException
     */
    public void add(int i) throws InterruptedException {
        lock.lock();
        try{
            //判断队列是否已满,有空位才能跳出循环完成添加
            while(count == array.length){
                fullCondition.await();
            }
            array[addIndex] = i;
            //如果刚好达到队列满时，重置
            if(++addIndex == array.length){
                addIndex = 0;
            }
            ++count;
            fullCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 移除队列元素，当队列是空时，线程被阻塞
     * @return
     */
    public Integer remove(){
        lock.lock();
        try{
            while(count == 0){
                emptyCondition.awaitUninterruptibly();
            }
            Integer x = array[removeIndex];
            array[removeIndex] = null;
            if(++removeIndex == array.length){
                removeIndex = 0;
            }
            --count;
            emptyCondition.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }

}
