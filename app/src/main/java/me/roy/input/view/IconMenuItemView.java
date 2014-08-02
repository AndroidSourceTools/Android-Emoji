package me.roy.input.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import me.roy.input.R;
import me.roy.input.entity.Source;

/**
 * Created by chenupt@gmail.com on 2014/8/1.
 * Description TODO
 */
public class IconMenuItemView extends FrameLayout{
    public final static String TAG = "IconMenuItemView";

    private Source source;

    private ImageView imageView;


    public IconMenuItemView(Context context) {
        this(context, null);
    }

    public IconMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconMenuItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.item_view_menu, this);
        imageView = (ImageView) findViewById(R.id.image_view);
    }

    public void setModel(Source source){
        this.source = source;
        bindView();
    }

    private void bindView(){
        imageView.setImageResource(source.getMenuResId());
    }
}
