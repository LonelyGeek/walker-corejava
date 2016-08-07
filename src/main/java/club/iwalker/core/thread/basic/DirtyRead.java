package club.iwalker.core.thread.basic;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * Created by wangchen on 2016/8/6.
 */
public class DirtyRead {
    private String username = "walker";
    private String password = "123";

    public synchronized void setValue(String username, String password) {
        this.username = username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue最终结果：username=" + username + "，passowrd = " + password);
    }
    /** synchronized */
    public  void getValue() {
        System.out.println("getValue最终结果：username=" + this.username + "，passowrd = " + this.password);
    }

    public static void main(String[] args) throws Exception {
        final DirtyRead dr = new DirtyRead();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("test", "456");
            }
        });
        t1.start();
        Thread.sleep(1000);
        dr.getValue();
    }

}
