package com.example.TweetSample;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.LetsGoTwee.R;
import models.Tweet;

import java.util.List;

/**
 * Created by monica on 28/5/15.
 */
public class CustomTweetDisplay extends ArrayAdapter {
    List<Tweet> tweetData;
    Activity activity;
    private static LayoutInflater inflater;
    private static final String LOG_NAME = CustomTweetDisplay.class.getName();
    public CustomTweetDisplay(Activity activity, String[] items){
        super(activity, R.layout.tweet_layout, items);
        inflater = activity.getWindow().getLayoutInflater();
    }

    public CustomTweetDisplay(TweetList activity,int resourceId, List<Tweet> tweetList) {
        super(activity, resourceId, tweetList);
        try{
            this.tweetData= tweetList;
            this.activity = activity;

            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            Log.d(LOG_NAME, e.getMessage());
        }

    }

    public static class ViewHolder{
        public TextView disp_header, disp_body, disp_footer;
    }

    @Override
    public View getView(int pos, View fetchView, ViewGroup parentView){
        View v = fetchView;

        final ViewHolder holder;
        try {
            if (null == v) {

                v = inflater.inflate(R.layout.tweet_layout, parentView, false);
                holder = new ViewHolder();
                holder.disp_header = (TextView)v.findViewById(R.id.tweet_header);
                holder.disp_body = (TextView) v.findViewById(R.id.tweet_body);
                holder.disp_footer = (TextView)v.findViewById(R.id.tweet_footer);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            holder.disp_header.setText(tweetData.get(pos).getHeader());
            holder.disp_body.setText(tweetData.get(pos).getBody());
            holder.disp_footer.setText(tweetData.get(pos).getFooter());
        }catch (Exception e){
            Log.d("Custom", e.getMessage());
        }
        return v;
    }
}
