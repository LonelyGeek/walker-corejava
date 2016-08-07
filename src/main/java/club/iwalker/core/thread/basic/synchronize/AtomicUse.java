package club.iwalker.core.thread.basic.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 关键字只具有可见性，没有原子性。要实现原子性建议使用atomic类的系列对象，
 * 支持原子性操作（注意atomic类只保证本身方法的原子性，并不保证多次操作的原子性）
 * 若保证多次操作的原子性还需加锁（synchronized）实现
 * Created by wangchen on 2016/8/7.
 */
public class AtomicUse {
    private static AtomicInteger count = new AtomicInteger(0);

    //多个addAndGet在一个方法内是非原子性的，需要加synchronized进行修饰，保证4个addAndGet整体原子性

    /**
     * synchronized
     */
    public synchronized int multiAdd() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4); //+10
        return count.get();
    }

    public static void main(String[] args) {
        final AtomicUse au = new AtomicUse();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ts.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(au.multiAdd());
                }
            }));
        }
        for (Thread t :
                ts) {
            t.start();
        }
    }
}
