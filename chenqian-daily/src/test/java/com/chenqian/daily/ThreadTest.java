package com.chenqian.daily;


import com.chenqian.daily.concurrent.VolatileTestDemo;
import com.chenqian.daily.config.ThreadPoolConfig;
import com.chenqian.daily.thread.MyRunnable;
import com.chenqian.daily.thread.MyThread;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


/**
 * @author chenQian
 * @date 2021年11月25日 11:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DailyApplication.class)
public class ThreadTest {
    @Autowired
    private ThreadPoolConfig config;

    @Autowired
    private VolatileTestDemo  demo;
    @Autowired
    @Qualifier("taskExecutor")
    ThreadPoolTaskExecutor taskExecutor;


    @Test
    public void testThread() {
        MyThread myThread=new MyThread();
        myThread.start();
    }

    @Test
    public void testRunnable() {
        MyRunnable runnable=new MyRunnable();
        new Thread(runnable).start();
    }

    @Test
    public void testExecutors() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //executorService.submit();
    }

    @Test
    public void testHappenBefore() throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                demo.makex();
            }
        });

        t.start();
        Thread.sleep(100);
        demo.makeTrue();
        while (true){

        }


    }

    @Test
    public void testReentrantLockTryLock() {
        ReentrantLock lock=new ReentrantLock();
        boolean b =false;
        try {
             b = lock.tryLock(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(b)lock.unlock();
        }
    }

    @Test
    public void testReentrantLockLockInterruptibly() {
        Thread thread=new Thread(){
            @Override
            public void run() {

        ReentrantLock lock=new ReentrantLock();
        try {
            lock.lockInterruptibly();
            TimeUnit.SECONDS.sleep(10);
            System.out.println("执行了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
            }
        };
        thread.start();
        //打断
        thread.interrupt();
    }
    public static ReentrantLock lock=new ReentrantLock(true);
    @Test
    public void testReentrantLockFair() {

        Thread thread=new Thread(){
            @Override
            public void run() {


                try {
                    lock.lockInterruptibly();
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println("执行了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        };
        thread.start();
        //打断
        thread.interrupt();
    }

    @Test
    public void testCountDownLatch() throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(10);
        List<Thread> list=new ArrayList<Thread>();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(){
                @SneakyThrows
                @Override
                public void run() {
                    int num =1;
                    for (int i1 = 0; i1 < 100000; i1++) {
                        num++;
                        if (num == 66666){
                            System.out.println(Thread.currentThread().getName());
                            latch.countDown();//线程不会在此阻塞
                        }
                    }
                }
            });
        }
        list.forEach(Thread::start);

        latch.await();
        System.out.println("end latch");
    }


    @Test
    public void testCyclicBarrier() {
        // var1 await()的调用次数 var2 要执行的任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> System.out.println("满人发车"));
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await(); //线程会在此阻塞
                        cyclicBarrier.await(); //线程会在此阻塞
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }


    static ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();
    @Test
    public void testReentrantReadWriteLock() {
        Runnable reader=()->read(readLock);
        Runnable writer=()->write(writeLock);

        for (int i = 0; i < 10; i++) new Thread(reader).start();
        for (int i = 0; i < 10; i++) new Thread(writer).start();

        sleep(100);
    }
    private void read(Lock lock){
        try {
            lock.lock();
            sleep(1);
            System.out.println("read");
        } finally {
            lock.unlock();
        }
    }
    private void write(Lock lock){
        try {
            lock.lock();
            sleep(1);
            System.out.println("write");
        } finally {
            lock.unlock();
        }
    }
    private void sleep(int num){
        try {
            TimeUnit.SECONDS.sleep(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    //场景：限流
    public void testSemaphore() {
        //信号量 默认非公平
        Semaphore semaphore = new Semaphore(2);
        //公平
//        Semaphore semaphore = new Semaphore(2);
        new Thread(()->{
            try {
                //获取信号量，如果获取成功 信号量会建议
                //如果信号量不足 会在此阻塞，知道获取成功
                semaphore.acquire();
                sleep(5);
                System.out.println("执行完毕 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放信号量 信号量会+1
                semaphore.release();
            }
        }).start();

        new Thread(()->{
            try {
                semaphore.acquire();
                sleep(5);
                System.out.println("执行完毕 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
        sleep(11);
    }

    @Test
    //线程间数据交换 未获得交换数据时会阻塞
    public void testExchanger() {
        Exchanger<String> exchanger=new Exchanger<>();

        new Thread(()->{
            String s ="T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s + " -----" +Thread.currentThread().getName());
        },"t1").start();
        new Thread(()->{
            String s ="T2";
            try {
                sleep(3);
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(s + " -----" +Thread.currentThread().getName());
        },"t2").start();

        sleep(10);
    }

    @Test
    //阻塞与取消阻塞某线程
    public void testLockSupport() {
        Thread t =new Thread(()->{
            for (int i = 0; i < 10; i++) {

                if ( i == 5){
                    //t线程阻塞
                    LockSupport.park();
                }
                System.out.println(i);
                sleep(1);
            }
        });
        t.start();

        sleep(20);
        //t线程取消阻塞 可以在park调用之前调用 提前取消阻塞
        LockSupport.unpark(t);
        sleep(10);
    }

    List<String> list=new ArrayList<>();
    @Test
    public void testVolatile(){
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                list.add(i+"");
                System.out.println(i);
            }
        }).start();
        new Thread(()->{
            while (true){
                if (list.size()>5){
                    System.out.println(list);
                }
            }
        }).start();
        sleep(10);
    }
    @Test
    public void testWaitAndNotify(){
        Object lock = new Object();
//        new Thread(()->{
//            synchronized (lock){
//
//                while (true){
//                    if (list.size()!=5){
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(list);
//                        lock.notify();
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(()->{
//            for (int i = 0; i < 10; i++) {
//                synchronized (lock){
//                    list.add(i+"");
//                    if (list.size() == 5){
//                            lock.notify();
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    System.out.println(i);
//                }
//            }
//        }).start();

        new Thread(()->{
            synchronized (lock){

                for (int i = 0; i < 5; i++) {
                    System.out.println(i*2);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(()->{
            synchronized (lock){


                for (int i = 1; i < 10; i+=2) {
                    System.out.println(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        sleep(10);
    }
}
