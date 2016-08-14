package club.iwalker.core.thread.executor;

import java.util.concurrent.*;

/**
 *
 * Future模式非常适合在处理很耗时很长的业务逻辑时进行使用，可以有效的减小系统的响应时间，提高系统的吞吐量。
 * Created by wangchen on 2016/8/14.
 */
public class UseFutrue implements Callable<String> {
    private String param;

    public UseFutrue(String param) {
        this.param = param;
    }

    /**
     *  这里是真实的业务逻辑，执行过程可能很慢
     * @return
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        //模拟执行耗时
        Thread.sleep(3000);
        String result = this.param + "处理完成";
        return result;
    }

    //主控制函数
    public static void main(String[] args) throws Exception {
        String queryStr = "query";

        //构造FutureTask，并且传入需要真正进行业务逻辑处理的类，该类一定是实现了Callable接口的类
        FutureTask<String> future = new FutureTask<String>(new UseFutrue(queryStr));
        FutureTask<String> future2 = new FutureTask<String>(new UseFutrue(queryStr+"2"));
        //创建一个固定线程的线程池且线程数为1，
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //这里提交任务future，则开启线程执行RealData的call()方法执行
        //submit和execute的区别：第一点是submit可以传入实现Callable接口的实例对象，第二点是submit方法有返回值

        Future f1 = executor.submit(future); //单独启动一个线程去执行的
        Future f2 = executor.submit(future2);
        System.out.println("请求完毕");

        try {
            // 这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
            System.out.println("处理实际的业务逻辑...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //调用获取数据方法，如果call() 方法没有执行完成，则依然会进行等待
        System.out.println("数据："+ future.get());
        System.out.println("数据："+ future2.get());

        executor.shutdown();
    }
}
