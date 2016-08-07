package club.iwalker.core.thread.basic;

/**
 * 对象锁的同步和异步问题
 * Created by wangchen on 2016/8/6.
 */
public class MyObject {
    public synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2 () {
        System.out.println(Thread.currentThread().getName());
    }

    /**
     * 分析：
     * t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法
     * t1线程先持有object对象的Lock锁，t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步
     */
    public static void main(String[] args) {
        final MyObject myObject = new MyObject();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                myObject.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                myObject.method2();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
