package me.roy.input.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.widget.AdapterView;
import it.sephiroth.android.library.widget.HListView;
import me.roy.input.R;
import me.roy.input.adapter.IconMenuAdapter;
import me.roy.input.adapter.IconPagerAdapter;
import me.roy.input.entity.IconEntity;
import me.roy.input.service.EmojiService;
import me.roy.input.service.EmojiSource;

/**
 * Created by chenupt@gmail.com on 2014/7/30.
 * Description TODO
 */
public class EmojiView extends LinearLayout{

    private WrapContentHeightViewPager viewPager;
    private IconPagerAdapter adapter;
    private CirclePageIndicator indicator;

    private ImageButton deleteBtn;
    private HListView horizontalListView;

    private EmojiService emojiService;
    private EmojiSource emojiSource;
    private IconMenuAdapter menuAdapter;

    public EmojiView(Context context) {
        this(context, null);
    }

    public EmojiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        emojiService = EmojiService.getInstance(getContext().getApplicationContext());
        emojiSource = EmojiSource.getInstance(getContext().getApplicationContext());
        onFinishInflate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.view_emoji, this);

        findViewById();
        initData();
        initView();
        action();
    }

    private void findViewById(){
        viewPager = (WrapContentHeightViewPager) findViewById(R.id.view_pager);
        indicator = (CirclePageIndicator) findViewById(R.id.page_indicator);
        deleteBtn = (ImageButton) findViewById(R.id.delete_btn);
        horizontalListView = (HListView) findViewById(R.id.h_list_view);
    }

    private void initData(){
        adapter = new IconPagerAdapter(getContext());
    }

    private void initView(){
        initIconMenu();
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);

        deleteBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onEmojiSelectedListener.delete();
            }
        });
    }

    private void action(){
        horizontalListView.setItemChecked(0, true);
        setIconAdapter(menuAdapter.getItem(0).getList());
    }




    private void setIconAdapter(List<IconEntity> iconEntityList){
        List<View> list = new ArrayList<View>();
        for(int i = 0; i < emojiService.getPageCount(iconEntityList.size()); i ++){
            IconGridViewPager itemView = new IconGridViewPager(getContext());
            itemView.setModel(emojiService.getIconList(i, iconEntityList), onEmojiSelectedListener);
            list.add(itemView);
        }
        adapter.setViewList(list);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initIconMenu(){
        menuAdapter = new IconMenuAdapter(getContext());
        menuAdapter.addList(emojiSource.getSourceList());
        horizontalListView.setAdapter(menuAdapter);
        horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setIconAdapter(menuAdapter.getItem(i).getList());
            }
        });
    }

    private OnEmojiSelectedListener onEmojiSelectedListener = new OnEmojiSelectedListener() {
        @Override
        public void onSelect(IconEntity iconEntity) {
            iconEditText.getText().insert(iconEditText.getSelectionStart(), emojiService.replaceEmoji(iconEntity.getKey()));
        }

        @Override
        public void delete() {
            KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
            iconEditText.dispatchKeyEvent(event);
        }
    };
    private IconEditText iconEditText;

    public interface OnEmojiSelectedListener {
        public void onSelect(IconEntity iconEntity);
        public void delete();
    }


    public void setEditText(IconEditText editText){
        this.iconEditText = editText;
        this.iconEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    EmojiView.this.setVisibility(View.GONE);
                }
                return false;
            }
        });
    }

}
