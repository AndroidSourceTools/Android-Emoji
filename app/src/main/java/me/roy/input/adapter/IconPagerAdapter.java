package me.roy.input.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenupt@gmail.com on 2014/7/29.
 * Description TODO
 */
public class IconPagerAdapter extends PagerAdapter {

    private List<View> viewList;

    public IconPagerAdapter(Context context) {
        super();
        viewList = new ArrayList<View>();
    }

    public IconPagerAdapter(Context context, List<View> list) {
        super();
        this.viewList = list;
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }

    public void setViewList(List<View> viewList) {
        this.viewList = viewList;
    }
}
