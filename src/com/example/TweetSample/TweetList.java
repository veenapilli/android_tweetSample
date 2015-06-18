package com.example.TweetSample;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.LetsGoTwee.R;
import models.Tweet;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by monica on 27/5/15.
 */
public class TweetList extends ListActivity {
    private ListView lv;
//    private String [] arrList;
    private ArrayAdapter<Tweet> tweetAdap;
    FileOutputStream fos; ObjectOutputStream oos;
    FileInputStream fis; ObjectInputStream ois;
    File file; List<Tweet> tweetList;
    private static final String FILENAME = "TweetFile.ser";
    private static final String LOG_NAME = TweetList.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_list);
        /*
        //Sample 1: Create a plain list of dummy strings
        arrList = new String[10];
        for(int i = 0; i< 10; i++)
            arrList[i] = "String " + i;
        tweetAdap = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrList);
        lv = (ListView) findViewById(R.id.tweetList);
        lv.setAdapter(tweetAdap);
        */

        /*
        // Sample dummy Tweet - static and same data
        tweetAdap = new CustomTweetDisplay(this, new String[10]);
        setListAdapter(tweetAdap);
         */

        /*
        // Sample dummy Tweet - static but different data
         */
        tweetList = new ArrayList<Tweet>();
        String dummyHead ="Tweet about ", dummyBody = "Someone said BLAH!!!";
        Date dt = new Date();
        for(int i = 0; i< 20; i++){
            Tweet tw = new Tweet(dummyHead+i, dummyBody,dt.toString(), this);
            tweetList.add(tw);

            //read and write tweets from a file

            if(openFile())
                if(writeFile())
                    Log.d(LOG_NAME, "Successfully wrote: "+ tw.toString());
            closeFile();
        }
       /* Log.d(LOG_NAME, "About to read data");
        try{
            if(openInputFile())
                if(readFile())
                    Log.d(LOG_NAME, "Read Data "+tweetList.size());
            closeInputFile();

            tweetAdap = new CustomTweetDisplay(this,0, tweetList);
            setListAdapter(tweetAdap);

        }catch (Exception e){
            Log.e(LOG_NAME, e.getMessage());
        }*/
        tweetAdap = new CustomTweetDisplay(this,0, tweetList);
        setListAdapter(tweetAdap);
            }

    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id){
//        TextView t = (TextView) v.findViewById(R.id.tweet_header);
//        t.setText("Clicked");
        Intent intent = new Intent(this, TweetDetail.class);
        startActivity(intent);
    }
    protected boolean openInputFile(){
        try{
            fis = openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);
            return true;
        }catch (Exception e){
            Log.d(LOG_NAME, e.getMessage());
            return false;
        }
    }
    protected boolean closeInputFile(){
        try{
            if(ois != null)
                ois.close();
            if(fis!= null)
                fis.close();
            return true;
        }catch (Exception e){
            Log.d(LOG_NAME, e.getMessage());
            return false;
        }
    }
    protected boolean readFile(){
        try{
            tweetList = (List<Tweet>)ois.readObject();
            return true;
        }catch (Exception e){
            Log.e(LOG_NAME, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    protected boolean openFile(){
        try{
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
// fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            return true;
        }catch (Exception e){
            Log.d(LOG_NAME, e.getMessage());
            return false;
        }
    }
    protected boolean closeFile(){
        try{
            if(oos != null)
                oos.close();
            if(fos!= null)
                fos.close();
            return true;
        }catch (Exception e){
            Log.d(LOG_NAME, e.getMessage());
            return false;
        }
    }
    protected boolean writeFile(){
        try{
            oos.writeObject(new Tweet());
            return true;
        }catch (Exception e){
            Log.e(LOG_NAME, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}