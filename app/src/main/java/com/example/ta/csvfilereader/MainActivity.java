package com.example.ta.csvfilereader;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CSVAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mList = (ListView)findViewById(R.id.mList);

        mAdapter = new CSVAdapter(this, -1);

        mList.setAdapter(mAdapter);



        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                Toast.makeText(v.getContext(), mAdapter.getItem(pos).getCapital(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
