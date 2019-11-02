package com.hjq.demo;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * 创建时间:  2019/4/14 12:13 AM
 * 项目作者:  韩湘子
 * 项目介绍:  TODO
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Picasso.get().load(String.valueOf(path)).into(imageView);
    }
}
