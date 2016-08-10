package club.iwalker.core.thread.design.master_worker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by wangchen on 2016/8/10.
 */
public class Worker implements Runnable {

    private ConcurrentLinkedQueue<Task> workerQueue;
    private ConcurrentHashMap<String, Object> resultMap;

    @Override
    public void run() {
        while (true) {
            Task input = this.workerQueue.poll();
            if (input == null) {
                break;
            }
            //真正的去做业务处理
            Object output = MyWorker.handle(input);
            this.resultMap.put(Integer.toString(input.getId()), output);
        }
    }

    public static Object handle(Task input) {
        return null;
    }

    /*private Object handle(Task input) {
        Object output = null;
        try {
            //表示处理task任务的耗时，可能是数据的加工，也可能是操作数据库。。。
            Thread.sleep(500);
            output = input.getPrice();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }*/

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public void setWorkerQueue(ConcurrentLinkedQueue<Task> workerQueue) {
        this.workerQueue = workerQueue;
    }
}
