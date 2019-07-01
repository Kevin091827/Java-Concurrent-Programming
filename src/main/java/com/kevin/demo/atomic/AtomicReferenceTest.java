package com.kevin.demo.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: Kevin
 * @Date:
 * @ClassName:AtomicReferenceTest
 * @Description: TODO
 */
public class AtomicReferenceTest {

    public static AtomicReference<User> atomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        User user = new User("kevin",15);
        atomicReference.set(user);
        User updateUser = new User("newKevin",20);
        atomicReference.compareAndSet(user,updateUser);
        System.out.println("--------->"+atomicReference.get().getName());
        System.out.println("--------->"+atomicReference.get().getAge());
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
