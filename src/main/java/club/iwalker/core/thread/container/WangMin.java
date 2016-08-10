package club.iwalker.core.thread.container;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangchen on 2016/8/9.
 */
public class WangMin implements Delayed {
    private String id; //身份证
    private String name; //姓名
    private long endTime; //截止时间

    private TimeUnit timeUnit = TimeUnit.SECONDS; //定义时间工具类

    public WangMin(String name, String id, long endTime) {
        this.id = id;
        this.name = name;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 用来判断是否到了截止时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        //return unit.convert(endTime, TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return endTime - System.currentTimeMillis();
    }

    /**
     * 相互比较排序用
     */
    @Override
    public int compareTo(Delayed delayed) {
        WangMin w = (WangMin) delayed;
        return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit) > 0 ? 1 : 0;
    }
}
