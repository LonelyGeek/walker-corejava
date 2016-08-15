package club.iwalker.core.thread.disruptor.base;


import com.lmax.disruptor.EventHandler;

/**
 * Created by wangchen on 2016/8/15.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event.getValue());
    }
}
