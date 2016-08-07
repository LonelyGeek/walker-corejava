package club.iwalker.core.thread.basic.synchronize;

/**
 * sychronized 重入
 * Created by wangchen on 2016/8/7.
 */
public class SyncDubbo2 {
    static class Main {
        public int i = 10;
        public synchronized void operationSup() {
            try{
                i --;
                System.out.println("Main print i = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Sub extends Main {
        public synchronized void operationSup() {
            try {
                while (i > 0) {
                    i --;
                    System.out.println("Sub print i = " + i);
                    Thread.sleep(100);
                    super.operationSup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub = new Sub();
                sub.operationSup();
            }
        });
        t1.start();
    }
}
