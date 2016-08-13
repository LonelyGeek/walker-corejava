package club.iwalker.core.thread.design.provider_consumer;

/**
 * Created by wangchen on 2016/8/11.
 */
public final class Data {
    private String id;
    private String name;

    public Data(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "{id:" + id + ", name:" + name + "}";
    }
}
