package com.ibs.android.remotesm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class settingActivity extends AppCompatActivity {
    public static String EXTRA_SETTING_TYPE="type";
    CardView cardLocal;
    CardView cardRemote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setTitle("Setting");

        cardLocal=(CardView)findViewById(R.id.card_Local);
        cardRemote=(CardView)findViewById(R.id.card_Remote);

        cardLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallIntent("local");
            }
        });

        cardRemote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CallIntent("remote");
            }
        });

    }
    private void CallIntent(String values)
    {
        Intent i=new Intent(this,LocalActivity.class);
        i.putExtra(EXTRA_SETTING_TYPE,values);
        startActivity(i);
    }


}
