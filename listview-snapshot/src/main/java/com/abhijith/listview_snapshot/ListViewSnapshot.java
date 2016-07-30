package com.abhijith.listview_snapshot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListViewSnapshot {

    private Context baseContext;
    private Context applicationContext;
    private ListView listViewSnapshot;
    private String path;
    private String timeStamp;
    private String mImageName;

    public ListViewSnapshot(ListView list, Context application, Context base){
        baseContext = base;
        Log.d("con: ", "Base: "+base);
        applicationContext = application;
        Log.d("con: ", "Application: "+application);
        listViewSnapshot = list;
        path = Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + applicationContext.getPackageName()
                + "/Files";

        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int minutes = c.get(Calendar.MINUTE);
        int hours = c.get(Calendar.HOUR_OF_DAY);
        timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date()) + "_" + hours + minutes + seconds;
        mImageName ="List_"+ timeStamp +".jpg";
    }

    public void convertWholeListViewItemsToSnapshot(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                makeBitmap();
            }
        }, 1000);
    }

    public void setPathtoSnapshot(String newPath){
        path = newPath;
    }

    public void renameSnapshot(String newName){
        mImageName = newName + ".jpg";
    }

    private void makeBitmap() {

        ListAdapter adapter  = listViewSnapshot.getAdapter();
        int itemscount       = adapter.getCount();
        int allitemsheight   = 0;
        List<Bitmap> bmps    = new ArrayList<Bitmap>();

        for (int i = 0; i < itemscount; i++) {

            View childView      = adapter.getView(i, null, listViewSnapshot);
            childView.measure(View.MeasureSpec.makeMeasureSpec(listViewSnapshot.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bmps.add(childView.getDrawingCache());
            allitemsheight+=childView.getMeasuredHeight();
        }

        Bitmap bigbitmap    = Bitmap.createBitmap(listViewSnapshot.getMeasuredWidth(), allitemsheight, Bitmap.Config.ARGB_8888);
        Canvas bigcanvas    = new Canvas(bigbitmap);
        bigcanvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        int iHeight = 0;

        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight+=bmp.getHeight();
        }



        File pictureFile = getOutputMediaFile(baseContext);
        if (pictureFile == null) {
            Log.d("TAG",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            bigbitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.close();

        } catch (FileNotFoundException e) {
            Log.d("TAG", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("TAG", "Error accessing file: " + e.getMessage());
        }

    }
    private File getOutputMediaFile(Context baseContext){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.


        File mediaStorageDir = new File(path);

        Log.i("path main", path );

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name with timestamp

        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + mImageName);
        return mediaFile;
    }
}
