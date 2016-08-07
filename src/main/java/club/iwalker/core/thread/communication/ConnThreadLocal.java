package club.iwalker.core.thread.communication;

/**
 * Created by wangchen on 2016/8/7.
 */
public class ConnThreadLocal {
    public static ThreadLocal<String> th = new ThreadLocal<>();

    public void setTh(String value) {
        th.set(value);
    }

    public void getTh() {
        System.out.println(Thread.currentThread().getName() + "：" + th.get());
    }

    public static void main(String[] args) {
        final ConnThreadLocal ct = new ConnThreadLocal();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ct.setTh("张三");
                ct.getTh();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    ct.getTh();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
