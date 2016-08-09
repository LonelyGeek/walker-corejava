package club.iwalker.core.thread.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangchen on 2016/8/9.
 */
public class UseConcurrentMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<>();
        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");
        chm.putIfAbsent("k4", "vvvv"); // 如果存在key，则不加，反之，加入
        for (Map.Entry<String, Object> me : chm.entrySet()) {
            System.out.println("key:" + me.getKey() + ", value:" + me.getValue());
        }
    }
}
