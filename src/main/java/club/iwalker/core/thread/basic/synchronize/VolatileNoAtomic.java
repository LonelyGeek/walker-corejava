package club.iwalker.core.thread.basic.synchronize;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 关键字不具备synchronized关键字的原子性（同步）
 * Created by wangchen on 2016/8/7.
 */
public class VolatileNoAtomic extends Thread {
    //private static volatile int count;

    private static AtomicInteger count = new AtomicInteger(0);
    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
            //count++;
            count.incrementAndGet();
        }
        System.out.println(count);
    }

    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        VolatileNoAtomic[] arr = new VolatileNoAtomic[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new VolatileNoAtomic();
        }
        for (VolatileNoAtomic noAtomic : arr) {
            noAtomic.start();
        }
    }
}
