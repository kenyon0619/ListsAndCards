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
    //宣告特約工人的經紀人
    private Handler mThreadHandler;
    //宣告特約工人
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

        //聘請一個特約工人，有其經紀人派遣其工人做事 (另起一個有Handler的Thread)
        mThread = new HandlerThread("name");
        //讓Worker待命，等待其工作 (開啟Thread)
        mThread.start();
        //找到特約工人的經紀人，這樣才能派遣工作 (找到Thread上的Handler)
        mThreadHandler=new Handler(mThread.getLooper());



        //  mThreadHandler.postDelayed(new Runnable() {
        //       public void run() {
        //      for (int k = 0 ; k<100 ; k++) {
        mUI_Handler.post(new Runnable() {
            //mThreadHandler.post(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub

                RVAdapter adapter = new RVAdapter(persons);
                //Bitmap myBitmap4 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Download/4.jpg");

                persons.add(3, new Person("Kenyon", "26 years old", BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Download/4.jpg")));
                adapter.notifyItemInserted(3);
            }
        });
        //     }
        // }
        // Actions to do after 10 seconds
        //}, 500);


        //for( int i = 0; i < 1000; i++) {
            //addPersons();
        //}
        //removePersons();

        /*
        mUI_Handler.post(new Runnable () {
            public void run() {
                // TODO Auto-generated method stub
                for (int k = 0 ; k<100 ; k++){
                    addPersons();
                }
            }

        });
        */

    }

    private void initializeData(){
        persons = new ArrayList<>();
        Bitmap myBitmap1 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Download/1.jpg");
        Bitmap myBitmap2 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Download/2.jpg");
        Bitmap myBitmap3 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/Download/3.jpg");
        //Resources res=getResources();
        //Bitmap myBitmap1 = BitmapFactory.decodeResource(res,R.drawable.emma);
        //Bitmap myBitmap2 = BitmapFactory.decodeResource(res,R.drawable.lavery);
        //Bitmap myBitmap3 = BitmapFactory.decodeResource(res,R.drawable.lillie);
        persons.add(new Person("Emma Wilson", "23 years old", myBitmap1));
        persons.add(new Person("Lavery Maiss", "25 years old", myBitmap2));
        persons.add(new Person("Lillie Watts", "35 years old", myBitmap3));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }

    private void addPersons(){
      //  mThreadHandler.postDelayed(new Runnable() {
     //       public void run() {
          //      for (int k = 0 ; k<100 ; k++) {
        mUI_Handler.post(new Runnable() {
                    //mThreadHandler.post(new Runnable() {
                        public void run() {
                            // TODO Auto-generated method stub

                            RVAdapter adapter = new RVAdapter(persons);
                            //Bitmap myBitmap4 = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Download/4.jpg");

                            persons.add(3, new Person("Kenyon", "26 years old", BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/Download/4.jpg")));
                            adapter.notifyItemInserted(3);
                        }
                    });
           //     }
           // }
            // Actions to do after 10 seconds
        //}, 500);
    }

    private void removePersons(){
        RVAdapter adapter = new RVAdapter(persons);
        persons.remove(1);
        adapter.notifyItemRemoved(1);
    }

}
