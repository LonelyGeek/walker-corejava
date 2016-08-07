package club.iwalker.core.thread.communication;

/**
 * double check 方式的单例模式
 * 两次判断也是是最安全的一种解决方案
 * 懒汉模式
 * Created by wangchen on 2016/8/7.
 */
public class DoubleSingleton {
    private static DoubleSingleton ds;

    public static DoubleSingleton getInstance() {
        if (ds == null) {
            try {
                //模拟初始化对象的准备时间
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (DoubleSingleton.class) {
            if (ds == null) {
                ds = new DoubleSingleton();
            }
        }
        return ds;
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleSingleton.getInstance().hashCode());
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleSingleton.getInstance().hashCode());
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleSingleton.getInstance().hashCode());
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(DoubleSingleton.getInstance().hashCode());
            }
        }, "t4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
