<<<<<<< HEAD
package com.example.aakash.imagemultiple;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class MarvelAdapter extends ArrayAdapter{

    private List<MarvelModel> marvelModelList;
    private int resourse;
    private LayoutInflater infilator;
    ViewHolder holder = null;

    public MarvelAdapter(Context context, int resource, List<MarvelModel> objects) {
        super(context, resource, objects);
        marvelModelList=objects;
        this.resourse=resource;
        infilator= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            holder =new ViewHolder();
            convertView=infilator.inflate(resourse,null);
            holder.image= (SquareImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(marvelModelList.get(position).getUrl(), holder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), DisplayImage.class);
                intent.putExtra("image",marvelModelList.get(position).getUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);

            }
        });

        return convertView;
    }
}
||||||| merged common ancestors
=======
package com.example.aakash.imagemultiple;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class MarvelAdapter extends ArrayAdapter{

    private List<MarvelModel> marvelModelList;
    private int resourse;
    private LayoutInflater infilator;
    ViewHolder holder = null;

    public MarvelAdapter(Context context, int resource, List<MarvelModel> objects) {
        super(context, resource, objects);
        marvelModelList=objects;
        this.resourse=resource;
        infilator= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            holder =new ViewHolder();
            convertView=infilator.inflate(resourse,null);
            holder.image= (SquareImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(marvelModelList.get(position).getUrl(), holder.image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
            }
        });
        return convertView;
    }
}
>>>>>>> 0ea380887a4b64861f2ce457c934d0733352b486
