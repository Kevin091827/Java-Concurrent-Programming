package com.kevin.demo.base_of_cconcurrency;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:SleepAndJoinDemo
 * @Description: TODO
 */
public class SleepAndJoinDemo {

    static class ThreadB2 extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " beg "
                        + System.currentTimeMillis());

                System.out.println(Thread.currentThread().getName() + " end "
                        + System.currentTimeMillis());
            }
        }
    }

    static class ThreadA2 extends Thread {

        private ThreadB2 threadB2;

        public ThreadA2(ThreadB2 threadB2) {
            this.threadB2 = threadB2;
        }

        @Override
        public void run() {
            synchronized (threadB2) {
                System.out.println(Thread.currentThread().getName() + " beg "
                        + System.currentTimeMillis());
                try {
                    System.out.println("wait之前：" + threadB2.isAlive());
                    threadB2.join();
                    System.out.println("wait 之后" + threadB2.isAlive());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end "
                        + System.currentTimeMillis());
            }
        }
    }

    public static void main(String[] args) {
        ThreadB2 threadB2 = new ThreadB2();
        threadB2.setName("BBB");
        ThreadA2 threadA2 = new ThreadA2(threadB2);
        threadA2.setName("AAA");

        threadA2.start();
        threadB2.start();
    }
}
