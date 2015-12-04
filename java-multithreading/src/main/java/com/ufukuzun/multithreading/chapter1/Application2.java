package com.ufukuzun.multithreading.chapter1;

public class Application2 {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runner());
        Thread thread2 = new Thread(new Runner());

        thread1.start();
        thread2.start();
    }

    static class Runner implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Merhaba " + i);
            }
        }

    }

}
