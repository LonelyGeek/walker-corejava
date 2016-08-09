package club.iwalker.core.thread.container;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by wangchen on 2016/8/9.
 */
public class UseCopyOnWrite {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<>();
    }
}
