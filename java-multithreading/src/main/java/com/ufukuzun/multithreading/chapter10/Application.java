package com.ufukuzun.multithreading.chapter10;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Application {

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

        runner.printBalance();
    }

}

class Runner {

    private Account account1 = new Account();
    private Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void acquireLocks(Lock firstLock, Lock secondLock) {
        while (true) {
            boolean isFirstLockAcquired = false;
            boolean isSecondLockAcquired = false;

            try {
                isFirstLockAcquired = firstLock.tryLock();
                isSecondLockAcquired = secondLock.tryLock();
            } finally {
                if (isFirstLockAcquired && isSecondLockAcquired) {
                    return;
                }

                if (isFirstLockAcquired) {
                    firstLock.unlock();
                }

                if (isSecondLockAcquired) {
                    secondLock.unlock();
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    public void firstThread() {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);

            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);

            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void printBalance() {
        System.out.println("Hesap 1'in bakiyesi: " + account1.getBalance());
        System.out.println("Hesap 2'in bakiyesi: " + account2.getBalance());
        System.out.println("Toplam bakiye: " + (account1.getBalance() + account2.getBalance()));
    }

}

class Account {

    private int balance = 10000;

    public static void transfer(Account sourceAccount, Account targetAccount, int amount) {
        sourceAccount.withdraw(amount);
        targetAccount.deposit(amount);
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

}