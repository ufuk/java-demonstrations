package com.ufukuzun.multithreading.chapter9;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Application2 {

    public static void main(String[] args) throws InterruptedException {
        final Runner runner = new Runner();

        Thread thread1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
            }
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

        private Condition condition = lock.newCondition();

        private void increment() {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }

        public void firstThread() throws InterruptedException {
            lock.lock();

            System.out.println("Thread 1 çalışıyor...");

            condition.await();

            System.out.println("Thread 1 devam ediyor...");

            try {
                increment();
            } finally {
                lock.unlock();
            }
        }

        public void secondThread() throws InterruptedException {
            Thread.sleep(2000);

            lock.lock();

            System.out.println("Thread 2 çalışıyor...");
            System.out.print("Devam etmek için 'Enter'a basınız: ");

            new Scanner(System.in).nextLine();

            condition.signal();

            System.out.println("Thread 2 devam ediyor...");

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
