package club.iwalker.core.thread.container;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wangchen on 2016/8/9.
 */
public class UsePriorityBlockingQueue {
    public static void main(String[] args) throws Exception {
        PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>();
        Task t1 = new Task();
        t1.setId(3);
        t1.setName("任务3");
        Task t2 = new Task();
        t2.setId(4);
        t2.setName("任务4");
        Task t3 = new Task();
        t3.setId(1);
        t3.setName("任务1");
        q.add(t1);
        q.add(t2);
        q.add(t3);
        for (Iterator<Task> iterator = q.iterator(); iterator.hasNext(); ) {
            Task task = iterator.next();
            System.out.println(task.getName());
        }
        //取出的时候按照优先级排序
        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
        System.out.println("容器：" + q);
        System.out.println(q.take().getId());
        System.out.println(q.take().getId());

    }
}
