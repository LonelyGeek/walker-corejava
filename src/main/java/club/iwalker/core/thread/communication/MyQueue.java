package club.iwalker.core.thread.communication;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangchen on 2016/8/7.
 */
public class MyQueue {
    //1 需要1个承装元素的集合
    private final LinkedList<Object> list = new LinkedList<>();

    //2 需要1个计数器
    private AtomicInteger count = new AtomicInteger(0);

    //3 需要指定上限和下限
    private final int minSize = 0;

    private final int maxSize;

    //4 构造方法，初始化常量
    public MyQueue(int size) {
        this.maxSize = size;
    }

    //5 初始化一个对象用于加锁
    private final Object lock = new Object();


    //put(anObject): 把anObject加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
    public void put(Object obj) {
        synchronized (lock) {
            while (count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 1 加入元素
            list.add(obj);
            // 2 计数器累加
            count.incrementAndGet();
            System.out.println("新加入的元素为：" + obj);
            // 3 通知另外一个线程（唤醒）
            lock.notify();//如果队列中没有元素，获取队列元素的方法在等待中，所以添加一个元素之后，需唤醒获取队列元素等待的方法
        }
    }

    //take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入.
    public Object take() {
        Object ret = null;
        synchronized (lock) {
            while (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 1 移除元素操作
            ret = list.removeFirst();
            // 2 计数器递减
            count.decrementAndGet();
            // 3 唤醒另外一个线程
            lock.notify();//如果队列元素已满，则获取一个元素之后，需要唤醒put等待的线程
        }
        return ret;
    }

    public int getSize() {
        return this.count.get();
    }

    public static void main(String[] args) {
        MyQueue mq = new MyQueue(5);
        mq.put("a");
        mq.put("b");
        mq.put("c");
        mq.put("d");
        mq.put("e");
        System.out.println("当前容器的长度：" + mq.getSize());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mq.put("f");
                mq.put("g");
            }
        }, "t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Object o1 = mq.take();
                System.out.println("移除的元素为：" + o1);
                Object o2 = mq.take();
                System.out.println("移除的元素为：" + o2);
            }
        }, "t2");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();


    }
}
