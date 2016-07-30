package com.abhijith.listview_snapshot_demo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.abhijith.listview_snapshot.ListViewSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        List<String> your_array_list = new ArrayList<String>();

        your_array_list.add("ping");
        your_array_list.add("pong");
        your_array_list.add("ping");
        your_array_list.add("pong");
        your_array_list.add("ping");
        your_array_list.add("pong");
        your_array_list.add("ping");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list);

        listView.setAdapter(arrayAdapter);

        ListViewSnapshot listViewSnapshot = new ListViewSnapshot(listView, getApplicationContext(), getBaseContext());

        //To set custom path to save the snapshot
        /*String newPath = Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files/NewPath";
        listViewSnapshot.renameSnapshot("newName");

        listViewSnapshot.setPathtoSnapshot(newPath);*/

        listViewSnapshot.convertWholeListViewItemsToSnapshot();

    }
}
