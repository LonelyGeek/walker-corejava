package club.iwalker.core.thread.basic.synchronize;

/**
 * Created by wangchen on 2016/8/7.
 *
 * volatile的作用就是强制线程到主内存（共享内存）里去读取变量，
 * 而不去线程工作内存区里去读取，从而实现了多个线程间的变量可见。
 * 也就是满足线程安全的可见性
 *
 */
public class RunThread extends Thread {
    /** volatile */
    private boolean isRunning = true;
    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        System.out.println("进入run方法....");
        while (isRunning == true) {
            //..
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        RunThread rt = new RunThread();
        rt.start();
        Thread.sleep(3000);
        rt.setRunning(false);
        System.out.println("isRunning的值已经被设置了false");
        rt.sleep(1000);
        System.out.println(rt.isRunning);
    }

}
