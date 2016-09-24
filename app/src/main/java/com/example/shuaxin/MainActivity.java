package com.example.shuaxin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.bigkoo.convenientbanner.CBViewHolderCreator;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private ConvenientBanner convenientBanner;// 顶部广告栏控件
    private List<String> networkImages = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listview;
    ListAdapter mAdapter;
    ArrayList<String> array = new ArrayList<>();
    boolean refresh = false;
    View v1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 15; i++) {
            String s = i + "";
            array.add(s);
        }
        for (int i = 0; i < 4; i++) {
            String s = "http://images.csdn.net/20150817/1.jpg";
            networkImages.add(s);
        }
        initView();
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 停止刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 5000); // 5秒后发送消息，停止刷新
    }

    private void initView() {
        listview = (ListView) findViewById(R.id.listview);
        mAdapter = new ListAdapter(MainActivity.this, array);
        listview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        swipeRefreshLayout.setDistanceToTriggerSync(100);//设置手指在屏幕下拉多少距离会触发下拉刷新
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh = false;
                array.clear();
                for (int i = 0; i < 15; i++) {
                    String s = i + "";
                    array.add(s);
                }
                mAdapter.setList(array);
                mAdapter.notifyDataSetChanged();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 停止刷新
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 5000); // 5秒后发送消息，停止刷新
            }
        });
        View v = LayoutInflater.from(this).inflate(R.layout.view_pager, null);
        convenientBanner = (ConvenientBanner) v.findViewById(R.id.convenientBanner);
        convenientBanner
                .setPages(
                        new CBViewHolderCreator<NetworkImageHolderView>() {
                            @Override
                            public NetworkImageHolderView createHolder() {
                                return new NetworkImageHolderView();
                            }
                        }, networkImages)
                .setPageIndicator(
                        new int[]{R.mipmap.choice_up,
                                R.mipmap.choice_down})
                .setPageIndicatorAlign(
                        ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        convenientBanner.startTurning(5000);
        listview.addHeaderView(v);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount
                        && totalItemCount > 0) {
                    //这里判断加载
                    if (!refresh && mAdapter.getCount() >= 15) {
                        refresh = true;
                        addFloodView();
                        bar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (int i = 0; i < 15; i++) {
                                    String s = i + "";
                                    array.add(s);
                                }
                                mAdapter.setList(array);
                                listview.removeFooterView(v1);
                                bar.setVisibility(View.GONE);
                                mAdapter.notifyDataSetChanged();
                                refresh = false;
                            }
                        }, 5000);
                    }
                }
            }
        });

    }

    ProgressBar bar;

    public void addFloodView() {
        v1 = LayoutInflater.from(this).inflate(R.layout.bottom_tip, null);
        bar = (ProgressBar) v1.findViewById(R.id.bar);
        listview.addFooterView(v1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (Util.isOnMainThread()) {
            Glide.with(getApplicationContext()).pauseRequests();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
