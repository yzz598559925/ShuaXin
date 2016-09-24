package com.example.shuaxin;


import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.CBPageAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by Sai on 15/8/4. 网络图片加载例子
 */
public class NetworkImageHolderView implements CBPageAdapter.Holder<String> {
    private ImageView imageView;
    Context mContext;
    //	List<MenusData> data;
//	UserInfoData user;
    Handler handler;
    View v;

    @Override
    public View createView(Context context) {
        // 你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        v = LayoutInflater.from(context).inflate(R.layout.layout, null);
        imageView = (ImageView) v.findViewById(R.id.iv);
//        imageView = new ImageView(context);
        this.mContext = context;
        return v;
    }

    //	public NetworkImageHolderView(List<MenusData> data, UserInfoData user,
//			Handler handler) {
//		this.data = data;
//		this.user = user;
//		this.handler = handler;
//	}
//    public NetworkImageHolderView(Handler handler) {
//        this.handler = handler;
//    }

    @Override
    public void UpdateUI(Context context, final int position, final String data) {
//		imageView.setImageResource(R.drawable.release_img_zone);
        Glide.with(context).load(data)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存各种imageview大小的相同的图片
                .into(imageView);
//		ImageLoader.getInstance().displayImage(data, imageView);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
//                MenusComm.onClick(getData(data), mContext, handler, user);
            }
        });

    }

//    public MenusData getData(String url) {
//        for (MenusData da : data) {
//            if (da.icon_url.equals(url)) {
//                return da;
//            }
//        }
//        return null;
//    }

}
