package club.iwalker.core.thread.basic.synchronize;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 * 使用String 常量加锁会出现死循环问题
 * 字符串常量是放在常量池里面的，多个线程访问的是同一个值（非引用），所以造成死循环
 * Created by wangchen on 2016/8/7.
 */
public class StringLock {

    public void method() {
        synchronized ("字符串常量") {
            try {
                while (true) {
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
                    Thread.sleep(1000);
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final StringLock stringLock = new StringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
