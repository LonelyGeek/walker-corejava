package club.iwalker.core.thread.executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wangchen on 2016/8/13.
 */
public class MyRejected implements RejectedExecutionHandler {
    public MyRejected(){}
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义处理....");
        System.out.println("当前被拒绝任务为：" + r.toString());
    }
}
