package me.roy.input.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.roy.input.entity.Source;

/**
 * Created by chenupt@gmail.com on 2014/7/31.
 * Description TODO
 */
public class EmojiSource {
    // application context
    private Context context;
    private static volatile EmojiSource instance = null;

    private Map<String, Integer> emojiMap;
    private List<Source> sourceList;

    private EmojiSource(Context context){
        this.context = context;
        addSource();
        initMapData();
    }

    private void addSource(){
        sourceList = new ArrayList<Source>();
        sourceList.add(new People());
        sourceList.add(new Nature());
    }

    public void initMapData(){
        emojiMap = new LinkedHashMap<String, Integer>();
        for(Source source : sourceList){
            source.initIcon(context, emojiMap);
        }
    }


    //-------------------------


    public Map<String, Integer> getEmojiMap() {
        return emojiMap;
    }

    public List<Source> getSourceList() {
        return sourceList;
    }

    public static EmojiSource getInstance(Context context) {
        if (instance == null) {
            synchronized (EmojiSource.class) {
                if (instance == null) {
                    instance = new EmojiSource(context);
                }
            }
        }
        return instance;
    }
}
