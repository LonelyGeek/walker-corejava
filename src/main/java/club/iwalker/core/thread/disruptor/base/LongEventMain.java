package club.iwalker.core.thread.disruptor.base;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by wangchen on 2016/8/15.
 */
public class LongEventMain {
    public static void main(String[] args) {
        //创建缓冲线程池
        ExecutorService executor = Executors.newCachedThreadPool();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //创建工厂
        LongEventFactory factory = new LongEventFactory();
        //创建bufferSize，也就是RingBuffer的大小，必须是2的N次方
        int ringBufferSize = 1024 * 1024;


        /**
         //BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
         WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
         //SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
         WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
         //YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
         WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
         */
        WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
        //创建disruptor

        // 1 第一个参数为工厂类对象，用于创建一个个的LongEvent，LongEvent是实际的消费数据
        // 2 第二个参数为缓冲区大小
        // 3 第三个参数是线程池 进行Disruptor 内部的数据接收处理调度
        // 4 第四个参数ProducerType.SINGLE 和 ProducerType.MULTI
        // 5 第五个参数是一种策略：WaitStrategy
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory,
                ringBufferSize, executor, ProducerType.SINGLE, YIELDING_WAIT);
        /*Disruptor<LongEvent> disruptor = new Disruptor<>(factory,
                ringBufferSize, threadFactory, ProducerType.SINGLE, YIELDING_WAIT);*/
        //连接消费事件方法
        disruptor.handleEventsWith(new LongEventHandler());
        //启动
        disruptor.start();

        //Disruptor 的事件发布过程是一个两阶段提交的过程
        //发布事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

//        LongEventProducer producer = new LongEventProducer(ringBuffer); hello world
        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);// 官方的

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; l < 100; l ++) {
            byteBuffer.putLong(0, l);
            producer.onData(byteBuffer);
//            Thread.sleep(1000);
        }

        disruptor.shutdown();//关闭 disruptor，方法会阻塞，直至所有时间都得到处理；
        executor.shutdown(); //关闭 disruptor 使用的线程池，如果需要的话，必须手动关闭， disruptor在shutdown时不会自动关闭

    }
}
