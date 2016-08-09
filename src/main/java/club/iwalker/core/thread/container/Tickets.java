package club.iwalker.core.thread.container;

import java.util.*;

/**
 * 多线程使用Vector或者HashTable的实例（简单线程同步问题）
 * Vector或者HashTable 以及 Collections.synchronized* 属于同步类容器
 * Created by wangchen on 2016/8/8.
 */
public class Tickets {
    public static void main(String[] args) {
        //初始化火车票池并添加火车票：避免现场同步可采用Vector替代ArrayList HashTable替代HashMap
        final Vector<String> tickets = new Vector<>();

        //Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());
        for (int i = 1; i <= 1000; i ++ ) {
            tickets.add("火车票" + i);
        }
        /*for (Iterator<String> iterator = tickets.iterator(); iterator.hasNext();) {
            String str = iterator.next();
            str.length();
            tickets.remove(20);
        }*/
        for (int i = 1; i <= 10; i ++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (tickets.isEmpty()) {
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
                    }
                }
            }, "线程" + i);
        }
    }
}
