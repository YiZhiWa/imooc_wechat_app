package com.example.imooc_wechat_app.view.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.imooc_wechat_app.utils.L;

public class ScaleTransformer implements ViewPager.PageTransformer {

    /**
     * 当我们从第一页划到第二页的时候
     * 第一页：全屏变成0.75大小划走
     * 第二页：从0.75状态进来变成1
     */
    private static final float MIN_SCALE = 0.6f;

    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        L.d(page.getTag() + ",pos = " + position);

        //a -> b
        //a  : position : (0 , -1)
        //b  : position : (1 , 0)


        //b -> a
        //a  : position : (-1 , 0)
        //b  : position : (0 , 1)


        if (position < -1) {

            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);

        } else if (position <= 1) {

            //左边这个页面
            if (position < 0) {
                //a->b  : position : (0 , -1)
                // 1 —> 0.75

                //              0.75     +      0.25       *      1-0
                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                page.setScaleX(scaleA);
                page.setScaleY(scaleA);

                //b->a  : position : (0 , 1)
                // 0.75 -> 1
//                float scaleB = MIN_SCALE + (1-MIN_SCALE) * (1 +position);

                //透明
                //a->b   1 -> 0.5
                float alphaA = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position);
                page.setAlpha(alphaA);

                //b->a   0.5 -> 1
//                MIN_ALPHA + (1-MIN_ALPHA)*(1+position);


            } else {//右边这个页面

                //a -> b
                //b  : position : (1 , 0)
                // 0.75 ->1

                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);

                //透明
                float alphaB = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position);
                page.setAlpha(alphaB);

                //b -> a
                //b  : position : (0 , 1)
                // 1 -> 0.75
//                MIN_SCALE + (1-MIN_SCALE)*(1-position);

//                MIN_ALPHA +(1-MIN_ALPHA) *(1-position);


            }

        } else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);

        }

    }

}
