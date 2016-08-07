package club.iwalker.core.thread.basic.synchronize;

/**
 * 同一对象属性的修改不会影响锁的情况
 * 还是对象引用问题
 * Created by wangchen on 2016/8/7.
 */
public class ModifyLock {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public synchronized void changeAttribute(String name, int age) {
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "开始");
            this.setName(name);
            this.setAge(age);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "修改对象内容为："
            +this.getName() + "，" + this.getAge());
            Thread.sleep(2000);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        final ModifyLock modifyLock = new ModifyLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifyLock.changeAttribute("张三", 20);
            }
        }, "t1");Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                modifyLock.changeAttribute("李四", 21);
            }
        }, "t2");
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
