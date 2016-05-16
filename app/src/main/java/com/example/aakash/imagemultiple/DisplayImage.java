package com.example.aakash.imagemultiple;

import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class DisplayImage extends AppCompatActivity {

    ImageView imageView;
    Button wallpaper, saveimage;
    ProgressBar progressBar;
    Bitmap showedImgae;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        imageView = (ImageView) findViewById(R.id.imageview);
        saveimage = (Button) findViewById(R.id.saveimage);
        wallpaper = (Button) findViewById(R.id.wallpaper);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("image");

        ImageLoader.getInstance().displayImage(url, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Toast.makeText(DisplayImage.this, "Loading Failed", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Toast.makeText(DisplayImage.this, "Loading Completed", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
                showedImgae = loadedImage;
                wallpaper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WallpaperManager m = WallpaperManager.getInstance(getApplicationContext());

                        try {
                            imageView.buildDrawingCache();
                            Bitmap bmap = imageView.getDrawingCache();
                            m.setBitmap(bmap);
                            Toast.makeText(DisplayImage.this, "Wallpaper Set!", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                saveimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        downloadImage();
                    }
                });
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Toast.makeText(DisplayImage.this, "Loading Canclled", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void downloadImage() {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/DCIM/ImageLoader");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "imagename-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            showedImgae.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Toast.makeText(DisplayImage.this, "Image Saved", Toast.LENGTH_SHORT).show();
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        getApplicationContext().sendBroadcast(mediaScanIntent);
    }
}
