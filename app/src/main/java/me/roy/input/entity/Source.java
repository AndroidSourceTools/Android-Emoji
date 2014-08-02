package me.roy.input.entity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenupt@gmail.com on 2014/8/1.
 * Description TODO
 */
public abstract class Source {

    private int menuResId;
    private List<IconEntity> list;
    public Source() {
        list = new ArrayList<IconEntity>();
        setMenuRes();
    }

    public List<IconEntity> getList() {
        return list;
    }

    public void setMenuResId(int menuResId) {
        this.menuResId = menuResId;
    }

    public abstract void initIcon(Context context, Map<String, Integer> map);

    public abstract void setMenuRes();

    public int getMenuResId() {
        return menuResId;
    }
}
