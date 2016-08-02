package com.hathy.listsandcards;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {

    private List<Person> persons;
    private RecyclerView rv;

    private Handler mUI_Handler = new Handler();
    private Handler mThreadHandler;
    private HandlerThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        mThread = new HandlerThread("name");
        mThread.start();
        mThreadHandler=new Handler(mThread.getLooper());

        mUI_Handler.postDelayed(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                RVAdapter adapter = new RVAdapter(persons);

                persons.add(3, new Person("Kenyon", "26 years old", BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Download/4.jpg")));
                adapter.notifyItemInserted(3);
            }
        }, 10000);

    }

    private void initializeData(){
        persons = new ArrayList<>();
        Bitmap myBitmap1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Download/1.jpg");
        Bitmap myBitmap2 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Download/2.jpg");
        Bitmap myBitmap3 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Download/3.jpg");

        persons.add(new Person("Emma Wilson", "23 years old", myBitmap1));
        persons.add(new Person("Lavery Maiss", "25 years old", myBitmap2));
        persons.add(new Person("Lillie Watts", "35 years old", myBitmap3));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }

}
