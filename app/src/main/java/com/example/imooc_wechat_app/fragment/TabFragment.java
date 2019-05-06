package com.example.imooc_wechat_app.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imooc_wechat_app.MainActivity;
import com.example.imooc_wechat_app.R;
import com.example.imooc_wechat_app.utils.L;

public class TabFragment extends Fragment {

    public static final String BUNDLE_KEY_TITLE ="key_title";
    protected View mView;
    private String mTitle;
    private TextView mTv_title;

    public interface OnTitleClickListener{
        void onClick(String title);
    }

    private OnTitleClickListener mListener;

    public void setOnTitleClickListener(OnTitleClickListener mListener) {
        this.mListener = mListener;
    }

    public static TabFragment newInstance(String mTitle){
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_TITLE,mTitle);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null){
            mTitle = arguments.getString(BUNDLE_KEY_TITLE,"");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_tab, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {
        mTv_title = mView.findViewById(R.id.tv_title);
        mTv_title.setText(mTitle);
        mTv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取activity对象
                // 写法1：
//                MainActivity activity = (MainActivity) getActivity();
//                activity.changeWeChatTab("微信 Changed！");

                // 写法2：


                //问题在于：我们Fragment会触发一些事件，Activity去响应这些事件

                if (mListener != null){
                    mListener.onClick("微信Changed！ ");
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public void changeTitle(String title){
        if (!isAdded())
            return;
        mTv_title.setText(title);
    }
}