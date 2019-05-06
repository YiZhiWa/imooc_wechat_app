package com.example.imooc_wechat_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.imooc_wechat_app.fragment.SplashFragment;
import com.example.imooc_wechat_app.view.transformer.ScaleTransformer;


public class SplashActivity extends AppCompatActivity {

    private ViewPager mVp_main;

    private int[] mResIds = new int[]{
            R.drawable.guide_image1,
            R.drawable.guide_image2,
            R.drawable.guide_image3,
            R.drawable.guide_image4,
            R.drawable.guide_image5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mVp_main = findViewById(R.id.vp_main);

        mVp_main.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return SplashFragment.newInstance(mResIds[i]);
            }

            @Override
            public int getCount() {
                return mResIds.length;
            }
        });

        mVp_main.setPageTransformer(true,new ScaleTransformer());
    }

}
