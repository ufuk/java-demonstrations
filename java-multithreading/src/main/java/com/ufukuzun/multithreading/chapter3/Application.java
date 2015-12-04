package com.ufukuzun.multithreading.chapter3;

public class Application {

    private int count = 0;

    public static void main(String[] args) {
        Application application = new Application();
        application.doCount();
    }

    public synchronized void increment() {
        count++;
    }

    private void doCount() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Sayaç: " + count);
    }

}
