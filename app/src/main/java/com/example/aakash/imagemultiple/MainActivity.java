package com.example.aakash.imagemultiple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class MainActivity extends AppCompatActivity {

    GridView gridview;
    ListView list;
    CheckConnection cd;
    Boolean isInternetPresent;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cd = new CheckConnection(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        if (isInternetPresent) {
            setContentView(R.layout.activity_main);
            gridview = (GridView) findViewById(R.id.gridview);
            text = (TextView) findViewById(R.id.text);

            DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplication())
                    .defaultDisplayImageOptions(defaultOptions)
                    .build();
            ImageLoader.getInstance().init(config); // Do it on Application start


            new JsonTaskMarvel(getApplicationContext(), gridview).execute("http://aakashbhatnagar.comule.com/several.php");

        } else {
           Toast.makeText(this,"NO Connection",Toast.LENGTH_LONG).show();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
 //       getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

}