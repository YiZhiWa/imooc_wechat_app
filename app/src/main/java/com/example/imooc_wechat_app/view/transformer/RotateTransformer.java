package com.example.imooc_wechat_app.view.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateTransformer implements ViewPager.PageTransformer {

    private static final int MAX_ROTATE = 15;


    //a -> b
    //a  : position : (0 , -1)
    //b  : position : (1 , 0)


    //b -> a
    //a  : position : (-1 , 0)
    //b  : position : (0 , 1)

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {
            page.setRotation(-MAX_ROTATE);
            page.setPivotY(page.getHeight());
            page.setPivotX(page.getWidth());

        } else if (position <= 1) {

            //左边这个页面
            if (position < 0) {

                page.setPivotY(page.getHeight());
                // 0.5w -> w
                page.setPivotX(0.5f * page.getWidth() + 0.5f * (-position) *page.getWidth());

                //(0,1) -> 0  -max
                page.setRotation(MAX_ROTATE * position);

            } else {//右边这个页面
                //a -> b
                //b  : position : (1 , 0)

                page.setPivotY(page.getHeight());
                // 0 , 0.5w
                page.setPivotX(0.5f * page.getWidth() + 0.5f * (1-position) );

                // max -> 0
                page.setRotation(MAX_ROTATE * position);

            }

        } else {
            page.setRotation(MAX_ROTATE);
            page.setPivotY(page.getHeight());
            page.setPivotX(0);
        }
    }
}
