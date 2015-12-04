package com.ufukuzun.multithreading.chapter9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Application1 {

    public static void main(String[] args) throws InterruptedException {
        final Runner runner = new Runner();

        Thread thread1 = new Thread(() -> {
            runner.firstThread();
        });

        Thread thread2 = new Thread(() -> {
            runner.secondThread();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        runner.printCount();
    }

    static class Runner {

        private int count = 0;

        private Lock lock = new ReentrantLock();

        private void increment() {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }

        public void firstThread() {
            lock.lock();
            try {
                increment();
            } finally {
                lock.unlock();
            }
        }

        public void secondThread() {
            lock.lock();
            try {
                increment();
            } finally {
                lock.unlock();
            }
        }

        public void printCount() {
            System.out.println("Sayaç: " + count);
        }

    }

}