package com.ufukuzun.multithreading.chapter6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(new Thread(new Processor(latch)));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
        }

        System.out.println("Program bitti.");
    }

}

class Processor implements Runnable {

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Thread başladı.");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        latch.countDown();
    }

}