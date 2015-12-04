package com.ufukuzun.multithreading.chapter11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    Connection.getInstance().connect();
                } catch (InterruptedException e) {
                }
            });
        }

        executorService.shutdown();
    }

}

class Connection {

    private static Connection instance = new Connection();

    private int connectionCount = 0;

    private Semaphore semaphore = new Semaphore(3);

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() throws InterruptedException {
        semaphore.acquire();

        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect() throws InterruptedException {
        synchronized (this) {
            connectionCount++;
            System.out.println("Bağlantı sayısı: " + connectionCount);
        }

        Thread.sleep(1000);

        synchronized (this) {
            connectionCount--;
        }
    }

}