package me.roy.input.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

import me.roy.input.service.EmojiService;

/**
 * Created by chenupt@gmail.com on 2014/8/1.
 * Description TODO
 */
public class IconEditText extends EditText{
    public IconEditText(Context context) {
        super(context);
        init();
    }

    public IconEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onTextContextMenuItem(int id) {
        if(id == android.R.id.paste){
            int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                String text = clipboard.getText().toString();
                getText().insert(getSelectionStart()
                        , EmojiService.getInstance(getContext().getApplicationContext()).replaceEmoji(text));
            } else {
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                if(clipboard.hasPrimaryClip()){
                    String text = clipboard.getPrimaryClip().getItemAt(0).getText().toString();
                    getText().insert(getSelectionStart()
                            , EmojiService.getInstance(getContext().getApplicationContext()).replaceEmoji(text));
                }
            }
            return true;
        }
        return super.onTextContextMenuItem(id);
    }

}
