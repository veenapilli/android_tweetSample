package models;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by monica on 28/5/15.
 */
public class Tweet implements Serializable{
    String header;
    String body;
    String footer;

    private static final String LOG_NAME=Tweet.class.getName();
    public Tweet() {
        this.header = "No Tweet";
        this.body = "Sample Tweet";
        this.footer = new Date().toString();
    }

    public Tweet(String header, String body, String footer, Context context) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }
    public String toString() {
        return this.getHeader()+" "+this.getBody()+" "+this.getFooter();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

}
