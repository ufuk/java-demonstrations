package com.ufukuzun.multithreading.chapter2;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        processor.start();

        Thread.sleep(10000);

        processor.shutdown();
    }

    static class Processor extends Thread {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                System.out.println("Hello");
            }
        }

        public void shutdown() {
            this.running = false;
        }

    }

}
