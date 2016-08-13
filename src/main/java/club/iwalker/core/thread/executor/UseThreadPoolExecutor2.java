package club.iwalker.core.thread.executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wangchen on 2016/8/13.
 */
public class UseThreadPoolExecutor2 implements Runnable {
    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            int temp = count.incrementAndGet();
            System.out.println("任务" + temp);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 无界的任务队列时：LinkedBlockingQueue。与有界队列相比，除非系统资源耗尽，否则无界的任务队列不存在任务入队失败的情况。
     * 当有新任务到来，系统的线程数小于corePoolSize时，则新建线程执行任务。
     * 当达到corePoolSize后，就不会继续增加。
     * 若后续仍有新的任务加入，而有没有空闲的线程资源，则任务直接进入队列等待。
     * 若任务创建和处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。

        // maxPoolSize 不起作用，主要看系统的线程数小于corePoolSize时
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
//        System.out.println(Runtime.getRuntime().availableProcessors());
        BlockingQueue<Runnable> queue =
//                new LinkedBlockingDeque<>();
        new ArrayBlockingQueue<>(10);
        ExecutorService executor = new ThreadPoolExecutor(
                5, //core
                10, //max
                120L, //2 分钟
                TimeUnit.SECONDS,
                queue
        );
        for (int i = 0; i < 20; i ++) {
            executor.execute(new UseThreadPoolExecutor2());
        }

        Thread.sleep(1000);
        System.out.println("queue size:" + queue.size());
        Thread.sleep(2000);
    }
}
