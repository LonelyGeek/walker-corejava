package club.iwalker.core.thread.design.master_worker;

/**
 * Created by wangchen on 2016/8/11.
 */
public class MyWorker extends Worker {
    public static Object handle(Task input) {
        Object output = null;
        try {
            //表示处理task任务的耗时，可能是数据的加工，也可能是操作数据库。。。
            Thread.sleep(500);
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
