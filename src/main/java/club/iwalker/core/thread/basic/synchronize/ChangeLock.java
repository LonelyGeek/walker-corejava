package club.iwalker.core.thread.basic.synchronize;

/**
 * 锁对象的改变问题
 * Created by wangchen on 2016/8/7.
 */
public class ChangeLock {
    private String lock = "lock";
    private void method() {
        synchronized (lock) {
            try {
                System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                lock = "change lock";
                Thread.sleep(2000);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ChangeLock changeLock = new ChangeLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        }, "t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
