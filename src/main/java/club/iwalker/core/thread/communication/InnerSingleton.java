package club.iwalker.core.thread.communication;

/**
 * 内部类方式实现的 单例模式
 * 多线程中最好用的一种解决方案，也是最安全的一种解决方案
 * Created by wangchen on 2016/8/7.
 */
public class InnerSingleton {
    private static class Singleton {
        private static Singleton single = new Singleton();
    }

    public static Singleton getInstance() {
        return Singleton.single;
    }
}
