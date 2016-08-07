package club.iwalker.core.thread.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用wait / notify 方法实现线程间的通信。（注意这两个方法都是object的类的方法，换句话说java为所有的对象都提供了这两个方法）
 * 1 wait 和 notify 必须配合 synchronized 关键字使用
 * 2 wait方法释放锁，notify方法不释放锁。
 *
 * 使用wait 和 notify会发生程序的阻塞，使用 CountDownLatch 不会阻塞，CountDownLatch后续会详细讲
 * Created by wangchen on 2016/8/7.
 */
public class ListAdd2 {
    private volatile static List<String> list = new ArrayList<>();

    public void add() {
        list.add("walker");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        final ListAdd2 listAdd2 = new ListAdd2();

        //1实例化出来一个lock
        //当使用wait和notify的时候，一定要配合着synchronized的关键字去使用
        //final Object lock = new Object();

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //synchronized (lock) {
                        for (int i = 0; i < 10; i++) {
                            listAdd2.add();
                            System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素...");
                            Thread.sleep(500);
                            if (listAdd2.size() == 5) {
                                System.out.println("已经发出通知..");
                                countDownLatch.countDown();
                                //lock.notify();
                            }
                        }
                    //}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //synchronized (lock) {
                    if (listAdd2.size() != 5) {
                        try {
                            System.out.println("t2进入....");
                            //lock.wait();
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
                    throw new RuntimeException();
                //}
            }
        }, "t2");

        t2.start();
        t1.start();
    }
}
