package club.iwalker.core.thread.basic.synchronize;

/**
 * synchronized重入
 * Created by wangchen on 2016/8/7.
 */
public class SyncDubbo1 {

    public void method1() {
        System.out.println("method1...");
        method2();
    }
    public void method2() {
        System.out.println("method2...");
        method3();
    }

    public void method3() {
        System.out.println("method3...");

    }

    public static void main(String[] args) {
        final SyncDubbo1 sd = new SyncDubbo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method1();
            }
        });
        t1.start();
    }
}
