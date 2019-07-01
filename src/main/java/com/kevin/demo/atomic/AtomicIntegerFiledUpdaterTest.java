package com.kevin.demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:AtomicIntegerFiledUpdaterTest
 * @Description: TODO
 */
public class AtomicIntegerFiledUpdaterTest {


    public static AtomicIntegerFieldUpdater<AtomicIntegerFiledUpdaterTest.User> atomicIntegerFieldUpdater
            = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFiledUpdaterTest.User.class,"age");

    public static void main(String[] args) {
        User user = new User("kevin",20);
        System.out.println("-------->"+atomicIntegerFieldUpdater.getAndIncrement(user));
        System.out.println("--------->"+atomicIntegerFieldUpdater.get(user));
    }

    static class User{
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
