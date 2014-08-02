package me.roy.input;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import me.roy.input.service.EmojiService;
import me.roy.input.view.EmojiView;
import me.roy.input.view.IconEditText;

/**
 * Created by chenupt@gmail.com on 2014/7/29.
 * Description TODO
 */
public class MainFragment extends Fragment {



    private IconEditText editText;
    private ImageButton showBtn;

    private EmojiView emojiView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViewById();
        initData();
        initView();
        action();
    }

    private void findViewById(){
        editText = (IconEditText) getView().findViewById(R.id.edit_text);
        showBtn = (ImageButton) getView().findViewById(R.id.show_btn);
        emojiView = (EmojiView) getView().findViewById(R.id.emoji_view);
    }

    private void initData(){

    }

    private void initView(){
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if(emojiView.getVisibility() == View.VISIBLE){
                    emojiView.setVisibility(View.GONE);
                }else{
                    emojiView.setVisibility(View.VISIBLE);
                }
            }
        });

        emojiView.setEditText(editText);
    }

    private void action(){
        SpannableString spannableString = EmojiService.getInstance(getActivity().getApplicationContext()).replaceEmoji("we are [1f600] and [1f601].[test]");
        editText.setText(spannableString);
    }




}
