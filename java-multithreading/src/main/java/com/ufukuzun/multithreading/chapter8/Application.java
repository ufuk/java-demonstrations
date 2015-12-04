package com.ufukuzun.multithreading.chapter8;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread producerThread = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }

}

class Processor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Üretici thread çalışıyor...");
            wait();
            System.out.println("Üretici devam ediyor...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);

        Scanner scanner = new Scanner(System.in);

        synchronized (this) {
            System.out.println("Tüketici thread çalışıyor...");
            System.out.print("Devam etmek için 'Enter'a basınız: ");
            scanner.nextLine();
            System.out.println("Tüketici devam ediyor...");
            notify();
            Thread.sleep(5000);
            System.out.println("Tüketici 5 saniye daha devam etti.");
        }
    }

}