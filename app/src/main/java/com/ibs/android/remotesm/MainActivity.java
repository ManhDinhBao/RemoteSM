package com.ibs.android.remotesm;

import android.app.ActionBar;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IBSAdapter.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;


    public static String EXTRA_LINK="linked";
    private RecyclerView mRecyclerView;
    private IBSAdapter mIbsAdapter;
    private ArrayList<Item> itemList;
    private RequestQueue mRequestQueue;
    String title="RemoteSM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView=(RecyclerView)findViewById(R.id.rvView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);
        pareJSON();

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setTitle(title);
    }

    private void pareJSON()
    {
        String URL="https://demo.openhab.org:8443/rest/sitemaps/demo/demo";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            title=response.getString("title");
                            setTitle(title);
                            JSONArray jsonArray= response.getJSONArray("widgets");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject objOutWidget=jsonArray.getJSONObject(i);
                                JSONArray jsonArrayWidgets = new JSONArray(objOutWidget.getString("widgets"));

                                for(int j=0;j<jsonArrayWidgets.length();j++) {

                                    JSONObject objInWidget=jsonArrayWidgets.getJSONObject(j);
                                    String linked = "null";
                                    String label=objInWidget.getString("label");
                                    if(jsonArrayWidgets.getJSONObject(j).isNull("linkedPage")==false) {
                                        JSONObject objItem = jsonArrayWidgets.getJSONObject(j).getJSONObject("linkedPage");
                                        linked=objItem.getString("link");
                                    }
                                    String type=objInWidget.getString("type");
                                    String state="";
                                    if(jsonArrayWidgets.getJSONObject(j).isNull("state")==false) {
                                        state=objInWidget.getString("state");
                                    }

                                    String icon=objInWidget.getString("icon");
                                    String iconURL = "https://demo.openhab.org:8443/icon/"+icon+"?state=null&format=PNG";
                                    itemList.add(new Item(label, iconURL,linked,type,state));
                                    Log.d("link",linked);
                                }
                            }

                            mIbsAdapter=new IBSAdapter(MainActivity.this,itemList);
                            mRecyclerView.setAdapter(mIbsAdapter);
                            mIbsAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }


    @Override
    public void onItemClick(int position) {
        Intent sub=new Intent(this,SubActivity.class);
        Item clickedItem=itemList.get(position);
        sub.putExtra(EXTRA_LINK,clickedItem.getLink());
        startActivity(sub);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.nav_about)
        {
            Intent i=new Intent(this,AboutActivity.class);
            startActivity(i);
        }
        else if (id==R.id.nav_setting)
        {
            Intent i= new Intent(this,settingActivity.class);
            startActivity(i);
        }
        return false;
    }
}
