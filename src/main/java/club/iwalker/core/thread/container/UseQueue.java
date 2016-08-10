package club.iwalker.core.thread.container;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wangchen on 2016/8/9.
 */
public class UseQueue {
    public static void main(String[] args) throws InterruptedException {
        /*//高性能无阻塞无界队列：ConcurrentLinkedQueue
        ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<>();
        q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");
        q.add("e");
        System.out.println(q.poll()); //a 从头部取出元素并从队列中删除
        System.out.println(q.size()); //4
        System.out.println(q.peek()); //b
        System.out.println(q.size()); //4*/

        /*ArrayBlockingQueue<String> array = new ArrayBlockingQueue<>(5);
        array.put("a");
        array.put("b");
        array.add("c");
        array.add("d");
        array.add("e");
        System.out.println(array.offer("a", 2, TimeUnit.SECONDS));*/

        /*//阻塞队列
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>();
        q.offer("a");
        q.offer("b");
        q.offer("c");
        q.offer("d");
        q.offer("e");
        q.add("f");*/
        /*System.out.println(q.size());
        for (Iterator<String> iterator = q.iterator(); iterator.hasNext();) {
            String str = iterator.next();
            System.out.println(str);
        }*/

        /*List<String> list = new ArrayList<>();
        System.out.println(q.drainTo(list, 3)); // 取前三个元素放入Collection集合中
        System.out.println(list.size());
        for (String str : list) {
            System.out.println(str);
        }*/

        /*SynchronousQueue<String> q = new SynchronousQueue<>(); //不允许加任何元素
        q.add("asdasd");*/

        final SynchronousQueue<String> q = new SynchronousQueue<>();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(q.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                q.add("asdasd");
            }
        });
        t2.start();
    }
}
