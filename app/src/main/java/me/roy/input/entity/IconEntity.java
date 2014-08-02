package me.roy.input.entity;

/**
 * Created by chenupt@gmail.com on 2014/7/30.
 * Description TODO
 */
public class IconEntity {

    private String id;
    private String key;
    private int res;

    public IconEntity() {
    }

    public IconEntity(String key, int res) {
        this.key = key;
        this.res = res;
    }

    public IconEntity(String id, String key, int res) {
        this.id = id;
        this.key = key;
        this.res = res;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
