package com.ufukuzun.multithreading.chapter4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();

    private List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) {
        Application app = new Application();

        long startTime = System.currentTimeMillis();

        app.work();

        long endTime = System.currentTimeMillis();

        System.out.println("Geçen zaman: " + (endTime - startTime));
        System.out.println("List 1'in boyutu: " + app.list1.size());
        System.out.println("List 2'nin boyutu: " + app.list1.size());
    }

    private void work() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }
    }

    private void process() {
        for (int i = 0; i < 1000; i++) {
            addNewIntegerToList1();
            addNewIntegerToList2();
        }
    }

    private void addNewIntegerToList1() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }

            list1.add(new Random().nextInt());
        }
    }

    private void addNewIntegerToList2() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }

            list2.add(new Random().nextInt());
        }
    }

}
