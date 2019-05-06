package com.example.imooc_wechat_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.imooc_wechat_app.fragment.SplashFragment;
import com.example.imooc_wechat_app.view.transformer.RotateTransformer;
import com.example.imooc_wechat_app.view.transformer.ScaleTransformer;


public class BannerActivity extends AppCompatActivity {

    private ViewPager mVp_main;

    private int[] mResIds = new int[]{
            R.drawable.banner_image1,
            R.drawable.banner_image2,
            R.drawable.banner_image3,
            R.drawable.banner_image4,
            R.drawable.banner_image5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        mVp_main = findViewById(R.id.vp_main);

        //预加载
        mVp_main.setOffscreenPageLimit(3);

        mVp_main.setPageMargin(40);//use dp

        mVp_main.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mResIds.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            /**
             * view  的创建
             * @param container
             * @param position
             * @return
             */
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view  =new View(container.getContext());

                view.setBackgroundResource(mResIds[position]);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(view);

                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }


        });

        mVp_main.setPageTransformer(true,new RotateTransformer());

    }

}
