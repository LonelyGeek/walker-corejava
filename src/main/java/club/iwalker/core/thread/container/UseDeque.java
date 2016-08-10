package club.iwalker.core.thread.container;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by wangchen on 2016/8/10.
 */
public class UseDeque {
    public static void main(String[] args) {
        LinkedBlockingDeque<String> dq = new LinkedBlockingDeque<>();
        dq.addFirst("a");
        dq.addFirst("b");
        dq.addFirst("c");
        dq.addFirst("d");
        dq.addFirst("e");
        dq.addLast("f");
        dq.addLast("g");
        dq.addLast("h");
        dq.addLast("i");
        dq.addLast("j");
        System.out.println("查看头元素：" + dq.peekFirst());
        System.out.println("查看尾元素：" + dq.peekLast());
        Object[] objs = dq.toArray();
        for (Object obj :
                objs) {
            System.out.println(obj);
        }
    }
}
