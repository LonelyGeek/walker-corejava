package club.iwalker.core.thread.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangchen on 2016/8/11.
 */

class Temp extends Thread {
    public void run() {
        System.out.println("run");
    }
}


public class ScheduledJob {
    public static void main(String[] args) {
        Temp command = new Temp();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduleTask = scheduler.scheduleWithFixedDelay(command, 1, 3, TimeUnit.SECONDS);
    }
}
