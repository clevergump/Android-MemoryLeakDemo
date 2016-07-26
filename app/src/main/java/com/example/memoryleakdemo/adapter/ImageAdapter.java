package com.example.memoryleakdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.memoryleakdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片加载的 adapter
 *
 * @author clevergump
 * @date 2016/7/24
 */
public class ImageAdapter extends BaseAdapter {

    private List<String> mImgUrls = new ArrayList<>();
    private Context mContext;

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mImgUrls.size();
    }

    @Override
    public Object getItem(int position) {
        return mImgUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setDatas(List<String> urls) {
        this.mImgUrls = urls;
    }

    public void addDatas(List<String> urls) {
        this.mImgUrls.addAll(urls);
    }

    public void addData(String url) {
        this.mImgUrls.add(url);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_img, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setPosition(position);
        return convertView;
    }

    private class ViewHolder {
        View contentView;
        ImageView iv;

        public ViewHolder(View contentView) {
            this.contentView = contentView;
            init();
        }

        private void init() {
            iv = (ImageView) contentView.findViewById(R.id.iv);
        }

        public void setPosition(int position) {
            if (iv == null) {
                return;
            }
            Glide.with(mContext.getApplicationContext()).load(mImgUrls.get(position)).asBitmap().placeholder(R.mipmap.ic_launcher).into(iv);

//            DisplayImageOptions options = new DisplayImageOptions.Builder()
//                    .bitmapConfig(Bitmap.Config.ARGB_8888)
//                    .imageScaleType(ImageScaleType.NONE)
//                    .showImageOnLoading(R.mipmap.ic_launcher)
//                    .showImageForEmptyUri(R.mipmap.ic_launcher)
//                    .showImageOnFail(R.mipmap.ic_launcher)
//                    .build();

//            DisplayImageOptions options = new DisplayImageOptions.Builder()
//                    .bitmapConfig(Bitmap.Config.RGB_565)
//                    .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
//                    .showImageOnLoading(R.mipmap.ic_launcher)
//                    .showImageForEmptyUri(R.mipmap.ic_launcher)
//                    .showImageOnFail(R.mipmap.ic_launcher)
//                    .cacheInMemory(true)
//                    .cacheOnDisk(true)
//                    .build();
//            ImageLoader.getInstance().displayImage(mImgUrls.get(position), new ImageViewAware(iv), options);
        }
    }
}