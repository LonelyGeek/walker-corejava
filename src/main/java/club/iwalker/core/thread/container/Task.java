package club.iwalker.core.thread.container;

/**
 * Created by wangchen on 2016/8/9.
 */
public class Task implements Comparable<Task>{
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Task task) {
        return this.getId() > task.getId() ? 1 : (this.getId() < task.getId() ? -1 : 0);
    }
}
