package com.example.imooc_wechat_app.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.imooc_wechat_app.R;

public class TabView extends FrameLayout {

    private ImageView mIvIcon;
    private ImageView mIvIconSelect;
    private TextView mTvTitle;

    private static final int COLOR_DEFAULT = Color.parseColor("#ff000000");
    private static final int COLOR_SELECT = Color.parseColor("#FF45C01A");


    public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.tab_view,this);

        mIvIcon = findViewById(R.id.iv_icon);
        mIvIconSelect = findViewById(R.id.iv_icon_select);
        mTvTitle = findViewById(R.id.tv_title);
        setProgress(0);

    }

    public void setProgress(float progress){
        mIvIcon.setAlpha(1-progress);
        mIvIconSelect.setAlpha(progress);
        mTvTitle.setTextColor(evaluate(progress,COLOR_DEFAULT,COLOR_SELECT));
    }
    //设置icon  text
    //方式一：抽取自定义属性，通过xml设置
    //方式二：直接对外开放方法设置
    public void setIconAndText(int icon ,int iconSelect,String text){
        mIvIcon.setImageResource(icon);
        mIvIconSelect.setImageResource(iconSelect);
        mTvTitle.setText(text);
    }

    public int evaluate(float fraction, int startValue, int endValue) {
        int startInt = (Integer) startValue;
        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endInt = (Integer) endValue;
        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        return (int)((startA + (int)(fraction * (endA - startA))) << 24) |
                (int)((startR + (int)(fraction * (endR - startR))) << 16) |
                (int)((startG + (int)(fraction * (endG - startG))) << 8) |
                (int)((startB + (int)(fraction * (endB - startB))));
    }
}
