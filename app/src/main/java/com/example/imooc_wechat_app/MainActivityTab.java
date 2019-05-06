package com.example.imooc_wechat_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.imooc_wechat_app.fragment.TabFragment;
import com.example.imooc_wechat_app.utils.L;
import com.example.imooc_wechat_app.view.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityTab extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp_main;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信","通讯录","发现","我"));
    private TabView mBtn_wechat;
    private TabView mBtn_friend;
    private TabView mBtn_find;
    private TabView mBtn_mine;

    private List<TabView> mTabs = new ArrayList<>();

    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    private static final String BUNDLE_KEY_POS = "bundle_key_pos";

    private int mCurTabPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        if (savedInstanceState != null){
            mCurTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS,0);
        }

        initView();
        initViewPagerAdapter();
        initListener();
        initEvents();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_POS,mVp_main.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void initEvents() {
        for (int i = 0; i < mTabs.size();i++){
            TabView tabView = mTabs.get(i);
            final int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVp_main.setCurrentItem(finalI,false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void initViewPagerAdapter() {
        mVp_main.setOffscreenPageLimit(mTitles.size());
        mVp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                TabFragment fragment = TabFragment.newInstance(mTitles.get(i));
                return fragment;
            }

            @Override
            public int getCount() {
                return mTitles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TabFragment tabFragment = (TabFragment) super.instantiateItem(container, position);
                mFragments.put(position,tabFragment);
                return tabFragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragments.remove(position);
                super.destroyItem(container, position, object);
            }
            //这样我们就做到了界面上有几个Fragment，我们mFragments里就有几个，而且是一一对应的
        });
        mVp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                L.d("onPageScrolled pos = "+position + " , positionOffset" + positionOffset );

                //左——>右  0—>1  left post,  right  post + 1  ,  positionOffset  0 ~ 1
                //left  progress: 1 - 0（1-positionOffset）; 绿色到灰色  right  progress:0 - 1（positionOffset） 灰色到绿色

                //右——>左  1—>0  left post,  right  post + 1  ,  positionOffset  1 ~ 0
                //left  progress: 0 - 1（1-positionOffset）; 灰色到绿色  right  progress:1 - 0（positionOffset） 绿色到灰色

                //left tab
                //right tab

                if (positionOffset > 0){
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position+1);
                    left.setProgress(1-positionOffset);
                    right.setProgress(positionOffset);
                }


            }

            @Override
            public void onPageSelected(int position) {
                L.d("onPageSelected pos = "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initView() {
        mVp_main = findViewById(R.id.vp_main);
        mBtn_wechat = findViewById(R.id.tab_wechat);
        mBtn_friend = findViewById(R.id.tab_friend);
        mBtn_find = findViewById(R.id.tab_find);
        mBtn_mine = findViewById(R.id.tab_mine);

        mBtn_wechat.setIconAndText(R.drawable.aio,R.drawable.ain,"微信");
        mBtn_friend.setIconAndText(R.drawable.aim,R.drawable.ail,"通讯录");
        mBtn_find.setIconAndText(R.drawable.aiq,R.drawable.aip,"发现");
        mBtn_mine.setIconAndText(R.drawable.ais,R.drawable.air,"我");

        mTabs.add(mBtn_wechat);
        mTabs.add(mBtn_friend);
        mTabs.add(mBtn_find);
        mTabs.add(mBtn_mine);

        setCurrentTab(mCurTabPos);
    }

    private void initListener() {
        mBtn_wechat.setOnClickListener(this);
        mBtn_friend.setOnClickListener(this);
        mBtn_find.setOnClickListener(this);
        mBtn_mine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_wechat:
                //获取第一个Fragment
                break;
        }
    }

    /**
     * 设置选中Tab
     * @param pos
     */
    private void setCurrentTab(int pos){
        for (int i = 0; i < mTabs.size();i++){
            TabView tabView = mTabs.get(i);
            if (i == pos){
                tabView.setProgress(1);
            }else {
                tabView.setProgress(0);
            }
        }
    }
}
