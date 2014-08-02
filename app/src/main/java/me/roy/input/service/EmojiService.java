package me.roy.input.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.roy.input.entity.IconEntity;

/**
 * Created by chenupt@gmail.com on 2014/7/30.
 * Description : The emojicon handler
 */
public class EmojiService {

    public final static String TAG = "EmojiService";

    private final String PREFIX_FILE_NAME = "emoji_";
    private final String SUFFIX_FILE_NAME = "";
    private final String PREFIX_KEY = "[";
    private final String SUFFIX_KEY = "]";
    private final String REGULAR_RULE = "\\[[^\\]]+\\]";

    private final int EMOJI_PAGE_SHOW_NUM = 21;

    public SpannableString replaceEmoji(String contentStr){
        SpannableString spannableString = new SpannableString(contentStr);
        Pattern pattern = Pattern.compile(REGULAR_RULE, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            Log.d(TAG, "key:" + key);
            int resId = 0;
            try {
                resId = EmojiSource.getInstance(context).getEmojiMap().get(key);
            } catch (NullPointerException e) {
                e.printStackTrace();
                continue;
            }
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            ImageSpan imageSpan = new ImageSpan(context, bitmap);
            spannableString.setSpan(imageSpan, matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableString;
    }

    public void replaceEmoji(Spannable spannableString){
        Pattern pattern = Pattern.compile(REGULAR_RULE, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            Log.d(TAG, "key:" + key);
            int resId = EmojiSource.getInstance(context).getEmojiMap().get(key);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
            ImageSpan imageSpan = new ImageSpan(context, bitmap);
            spannableString.setSpan(imageSpan, matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }


    public List<IconEntity> getIconList(int page, List<IconEntity> list){
        List<IconEntity> resultList = new ArrayList<IconEntity>();

        int start = page * EMOJI_PAGE_SHOW_NUM;
        int end = (page + 1) * EMOJI_PAGE_SHOW_NUM;
        if(end > list.size()){
            end = list.size();
        }
        for(int i = start; i < end; i ++){
            resultList.add(list.get(i));
        }
        return resultList;
    }

    public int getPageCount(int count){
        return count / EMOJI_PAGE_SHOW_NUM + 1;
    }


    // add all emojicons to a map for that text can find the emojicon.
    public IconEntity addEmojToMap(Context context, String text, Map<String, Integer> map){
        String key = PREFIX_KEY + text + SUFFIX_KEY;
        int resId = context.getResources().getIdentifier(PREFIX_FILE_NAME + text + SUFFIX_FILE_NAME, "drawable", context.getPackageName());
        map.put(key, resId);
        return new IconEntity(key, resId);
    }

    private static volatile EmojiService instance = null;
    private Context context;

    private EmojiService(Context context){
        this.context = context;
    }

    public static EmojiService getInstance(Context context) {
        if (instance == null) {
            synchronized (EmojiService.class) {
                if (instance == null) {
                    instance = new EmojiService(context);
                }
            }
        }
        return instance;
    }
}
