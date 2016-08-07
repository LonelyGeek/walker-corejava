package club.iwalker.core.thread.basic;

/**
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁（Lock），
 *
 * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）。
 * Created by wangchen on 2016/8/6.
 * 多个线程访问多个实例
 * static 方法 加 synchronized 相当于在class上面加锁，属于独占锁
 */
public class MultiThread {
    private static int num = 0;
    /** static */
    public static synchronized void printNum(String tag) {
        try {
            if ("a".equals(tag)) {
                num = 100;
                System.out.println("tag a, set num over!");
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("tab b, set num over!");
            }
            System.out.println("tag" + tag + ", num = " + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //注意观察run方法输出顺序
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                m1.printNum("a");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                m2.printNum("b");
            }
        });
        t1.start();
        t2.start();
    }
}
