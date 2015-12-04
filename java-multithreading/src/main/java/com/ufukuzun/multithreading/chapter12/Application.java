package com.ufukuzun.multithreading.chapter12;

import java.util.Random;
import java.util.concurrent.*;

public class Application {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                return random.nextInt();
            }
        });

        executorService.shutdown();

        try {
            Integer result = future.get(10, TimeUnit.MILLISECONDS);
            System.out.println("Sonuç: " + result);
        } catch (ExecutionException e) {
            System.out.println("Çalışma zamanı hatası!");
        } catch (TimeoutException e) {
            System.out.println("Zaman aşımı!");
        } catch (InterruptedException e) {
        }
    }

}