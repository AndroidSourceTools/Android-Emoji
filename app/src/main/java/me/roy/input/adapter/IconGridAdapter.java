package me.roy.input.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import me.roy.input.base.BaseListAdapter;
import me.roy.input.entity.IconEntity;
import me.roy.input.view.IconGridItemView;

/**
 * Created by chenupt@gmail.com on 2014/7/29.
 * Description TODO
 */
public class IconGridAdapter extends BaseListAdapter<IconEntity> {

    public IconGridAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = new IconGridItemView(getContext());
        }
        ((IconGridItemView) view).setModel(getItem(i), getList());
        return view;
    }


}
