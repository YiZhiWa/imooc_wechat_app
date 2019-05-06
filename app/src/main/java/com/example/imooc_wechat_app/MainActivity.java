package com.example.imooc_wechat_app;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.imooc_wechat_app.fragment.TabFragment;
import com.example.imooc_wechat_app.utils.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp_main;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信","通讯录","发现","我"));
    private Button mBtn_wechat;
    private Button mBtn_friend;
    private Button mBtn_find;
    private Button mBtn_mine;

    private List<Button> mTabs = new ArrayList<>();

    private SparseArray<TabFragment> mFragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L.d("activity onCreate");
        initView();
        initViewPagerAdapter();
        initListener();
    }

    private void initViewPagerAdapter() {

        mVp_main.setOffscreenPageLimit(mTitles.size());

        mVp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                TabFragment fragment = TabFragment.newInstance(mTitles.get(i));

                if (i==0){
                    fragment.setOnTitleClickListener(new TabFragment.OnTitleClickListener() {
                        @Override
                        public void onClick(String title) {
                            changeWeChatTab(title);
                        }
                    });
                }

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
                    Button left = mTabs.get(position);
                    Button right = mTabs.get(position+1);
                    left.setText(1-positionOffset + "");
                    right.setText(positionOffset + "");
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
        mBtn_wechat = findViewById(R.id.btn_wechat);
        mBtn_friend = findViewById(R.id.btn_friend);
        mBtn_find = findViewById(R.id.btn_find);
        mBtn_mine = findViewById(R.id.btn_mine);

        mTabs.add(mBtn_wechat);
        mTabs.add(mBtn_friend);
        mTabs.add(mBtn_find);
        mTabs.add(mBtn_mine);
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
                TabFragment tabFragment = mFragments.get(0);
                if (tabFragment != null){
                    tabFragment.changeTitle("微信 Changed!");
                }
                break;
        }
    }


    public void changeWeChatTab(String title){
        mBtn_wechat.setText(title);
    }
}
