package club.iwalker.core.thread.executor;

import java.util.concurrent.CountDownLatch;

/**
 * 针对一个线程
 * Created by wangchen on 2016/8/14.
 */
public class UseCountDownLatch {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);//需要唤醒的次数
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入线程" + Thread.currentThread().getName() + "等待其他线程处理完成...");
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + "线程继续执行...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "进行初始化操作...");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "线程初始化完毕，通知t1线程继续...");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + "进行初始化操作...");
                    Thread.sleep(4000);
                    System.out.println(Thread.currentThread().getName() + "线程初始化完毕，通知t1线程继续...");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();


    }
}
