package com.ibs.android.remotesm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

import static com.ibs.android.remotesm.MainActivity.EXTRA_LINK;

public class SubActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private IBSAdapter mIbsAdapter;
    private ArrayList<Item> itemList;
    private RequestQueue mRequestQueue;
    String URL="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent=getIntent();
        URL=intent.getStringExtra(EXTRA_LINK);

        mRecyclerView=(RecyclerView)findViewById(R.id.rvViewSub);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);
        pareJSON();
    }

    private void pareJSON()
    {
        //String URL="https://demo.openhab.org:8443/rest/sitemaps/demo/0000";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray= response.getJSONArray("widgets");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject objWidget=jsonArray.getJSONObject(i);
                                JSONObject objItem=objWidget.getJSONObject("item");
                                    String label=objWidget.getString("label");
                                    String icon = "https://demo.openhab.org:8443/icon/firstfloor?state=null&format=PNG";
                                    String linked=objItem.getString("link");
                                    itemList.add(new Item(label, icon,linked));
                                    Log.d("link",linked);

                            }

                            mIbsAdapter=new IBSAdapter(SubActivity.this,itemList);
                            mRecyclerView.setAdapter(mIbsAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();;
            }
        });

        mRequestQueue.add(request);

    }
}
