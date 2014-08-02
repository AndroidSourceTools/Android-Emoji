package me.roy.input.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import me.roy.input.base.BaseListAdapter;
import me.roy.input.entity.Source;
import me.roy.input.view.IconMenuItemView;

/**
 * Created by chenupt@gmail.com on 2014/8/1.
 * Description : icon menu adapter
 */
public class IconMenuAdapter extends BaseListAdapter<Source> {

    public IconMenuAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = new IconMenuItemView(getContext());
        }
        ((IconMenuItemView) view).setModel(getItem(i));
        return view;
    }
}
