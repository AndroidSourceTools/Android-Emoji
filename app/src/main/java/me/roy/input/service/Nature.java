package me.roy.input.service;

import android.content.Context;

import java.util.Map;

import me.roy.input.R;
import me.roy.input.entity.IconEntity;
import me.roy.input.entity.Source;

/**
 * Created by chenupt@gmail.com on 2014/7/31.
 * Description TODO
 */
public class Nature extends Source {

    @Override
    public void initIcon(Context context, Map<String, Integer> map){
        String text = "1f";
        for(int i = 340; i < 390; i ++){
            IconEntity iconEntity = EmojiService.getInstance(context).addEmojToMap(context, text + i, map);
            getList().add(iconEntity);
        }
    }

    @Override
    public void setMenuRes() {
        setMenuResId(R.drawable.orca_emoji_category_nature);
    }


}
