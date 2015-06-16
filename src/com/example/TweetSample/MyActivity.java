package com.example.TweetSample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.LetsGoTwee.R;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private static final String LOG_NAME = MyActivity.class.getName();
    Button login;
    String SP_USER = "TWEE_USER", SP_PASS = "TWEE_PASS";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        String sp_UserName = null, sp_Userpass = null;
        SharedPreferences sp_present = getSharedPreferences("Twee", MODE_PRIVATE);
        sp_UserName = sp_present.getString(SP_USER, "");
        sp_Userpass = sp_present.getString(SP_PASS,"");
        if(!sp_UserName.equals("") && !sp_Userpass.equals("")){
            Intent intent = new Intent(this, TweetList.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        login = (Button) findViewById(R.id.main_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login.setText("Logging in");
                EditText et_username = (EditText)findViewById(R.id.val_userName);
                String str_userName =null, str_userPass = null;
                str_userName = et_username.getText().toString();
                EditText et_password = (EditText)findViewById(R.id.val_passName);
                str_userPass = et_password.getText().toString();
                Log.d(LOG_NAME, "UserName: " + str_userName + " Pass: " + str_userPass);
                if(null!=str_userName && !str_userName.equals("") && !str_userName.startsWith(" ")){
                    if(null!=str_userPass){
                        SharedPreferences sp = getSharedPreferences("Twee", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(SP_USER, str_userName);
                        editor.putString(SP_PASS, str_userPass);
                        editor.commit();
                    }
                }
                Intent intent = new Intent(MyActivity.this, TweetList.class);
                startActivity(intent);
            }
        });

    }
}
