package me.roy.input.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

import me.roy.input.R;
import me.roy.input.entity.IconEntity;

/**
 * Created by chenupt@gmail.com on 2014/7/29.
 * Description TODO
 */
public class IconGridItemView extends FrameLayout {

    public final static String TAG = "IconGridItemView";

    private IconEntity iconEntity;
    private List<IconEntity> iconEntityList;

    private ImageView imageView;

    private EmojiView.OnEmojiSelectedListener onEmojiSelectListener;

    public IconGridItemView(Context context) {
        this(context, null);
    }

    public IconGridItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconGridItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.item_view_icon, this);
        imageView = (ImageView) findViewById(R.id.image_view);
    }

    public void setModel(IconEntity entity, List<IconEntity> list){
        this.iconEntity = entity;
        this.iconEntityList = list;
        bindView();
    }



    private void bindView(){
        Log.d(TAG, "bindView:" + iconEntity.getKey());
        imageView.setImageResource(iconEntity.getRes());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // make grid view items square
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
