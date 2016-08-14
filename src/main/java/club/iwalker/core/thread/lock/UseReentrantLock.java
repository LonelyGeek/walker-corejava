package club.iwalker.core.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁，在需要进行同步的代码部分加上锁定，但不要忘记最后一定要释放锁定，不然会造成锁永远无法释放，其他线程永远进不来的结果
 * Created by wangchen on 2016/8/14.
 */
public class UseReentrantLock {
    private Lock lock = new ReentrantLock();
    public void method1() {
        try {
            lock.lock();
            System.out.println("当前线程" + Thread.currentThread().getName() + "进入method1...");
            Thread.sleep(1000);
            System.out.println("当前线程" + Thread.currentThread().getName() + "退出method1...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }public void method2() {
        try {
            lock.lock();
            System.out.println("当前线程" + Thread.currentThread().getName() + "进入method2...");
            Thread.sleep(1000);
            System.out.println("当前线程" + Thread.currentThread().getName() + "退出method2...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseReentrantLock ur = new UseReentrantLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ur.method1();
                ur.method2();
            }
        }, "t1");

        t1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(ur.lock.);
    }
}
