package club.iwalker.core.thread.disruptor.base;

import com.lmax.disruptor.EventFactory;

/**
 * 需要让disruptor为我们创建事件，我们同时还声明了一个EventFactory来实例化Event对象
 * Created by wangchen on 2016/8/15.
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
