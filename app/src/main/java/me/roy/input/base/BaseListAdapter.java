package me.roy.input.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenupt@gmail.com on 2014/5/18.
 * Description : TODO
 */
public abstract class BaseListAdapter<T> extends BaseAdapter{

    protected List<T> list;
    protected Context context;
    
    public BaseListAdapter(Context context) {
        list = new ArrayList<T>();
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList(){
        return list;
    }

    public void addList(List<T> list){
        this.list.addAll(list);
    }

    public void addItem(T item){
        this.list.add(item);
    }


    public void clearList(){
        this.list.clear();
    }
    
    public void remove(int position){
    	this.list.remove(position);
    }
    
    public Context getContext(){
    	return context;
    }


}
