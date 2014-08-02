package me.roy.input.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;

import java.util.List;

import me.roy.input.R;
import me.roy.input.entity.IconEntity;
import me.roy.input.adapter.IconGridAdapter;

/**
 * Created by chenupt@gmail.com on 2014/7/29.
 * Description TODO
 */
public class IconGridViewPager extends FrameLayout {

    public final static String TAG = "IconGridViewPager";

    private GridView gridView;
    private IconGridAdapter adapter;
    private EmojiView.OnEmojiSelectedListener onEmojiSelectedListener;

    private List<IconEntity> iconEntityList;

    public IconGridViewPager(Context context) {
        this(context, null);
    }

    public IconGridViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconGridViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.view_pager_icon, this);

        gridView = (GridView) findViewById(R.id.grid_view);
        adapter = new IconGridAdapter(getContext());
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "position:" + i);
                if(onEmojiSelectedListener != null){
                    onEmojiSelectedListener.onSelect(adapter.getItem(i));
                }
            }
        });
    }

    public void setModel(List<IconEntity> list, EmojiView.OnEmojiSelectedListener onEmojiSelectedListener){
        this.iconEntityList = list;
        this.onEmojiSelectedListener = onEmojiSelectedListener;
        bindView();
    }

    private void bindView(){
        adapter.setList(iconEntityList);
        adapter.notifyDataSetChanged();
    }

}