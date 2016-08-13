package club.iwalker.core.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangchen on 2016/8/11.
 */
public class UseExecutors {
    public static void main(String[] args) {
        /*
        public ThreadPoolExecutor(int corePoolSize,  初始化线程数
                                  int maximumPoolSize,  最大线程数
                                  long keepAliveTime,  线程空闲时间
                                  TimeUnit unit,  线程空闲时间单位
                                  BlockingQueue<Runnable> workQueue, 发生线程阻塞时，存放等待线程的队列
                                  ThreadFactory threadFactory,
                                  RejectedExecutionHandler handler) {...} 需要作出拒绝时，执行的处理函数

         */
        //底层都是通过ThreadPoolExecutor类实现的
        ExecutorService pool = Executors.newFixedThreadPool(10);//固定线程池 阻塞存放队列 LinkedBlockingQueue（无界限队列）
        Executors.newSingleThreadExecutor(); //单个线程线程池 阻塞存放队列 LinkedBlockingQueue （无界限队列）
        Executors.newCachedThreadPool();// 阻塞存放队列 SynchronousQueue （无缓冲队列）
        ExecutorService service = Executors.newScheduledThreadPool(10); // 阻塞存放队列 DelayedWorkQueue （带有时间延迟的队列）

        //四类线程池底层核心都是通过ThreadPoolExecutor类创建的
        //cache fiexed single
//        service.execute();

    }
}
