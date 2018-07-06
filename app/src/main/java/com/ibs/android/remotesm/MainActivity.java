package com.ibs.android.remotesm;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private IBSAdapter mIbsAdapter;
    private ArrayList<Item> itemList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=(RecyclerView)findViewById(R.id.rvView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);
        pareJSON();
    }

    private void pareJSON()
    {
        String URL="https://demo.openhab.org:8443/rest/sitemaps/demo/demo";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray= response.getJSONArray("widgets");

                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject widget=jsonArray.getJSONObject(i);
                                JSONArray jsonArray1 = new JSONArray(jsonArray.getJSONObject(i).getString("widgets"));

                                for(int j=0;j<jsonArray1.length();j++) {
                                    //String label=widget.getString("icon");
                                    String label=jsonArray1.getJSONObject(j).getString("label");
                                    //String label = "test";
                                   // String icon = widget.getString("https://demo.openhab.org:8443/icon/firstfloor?state=null&format=PNG");
                                    String icon = "https://demo.openhab.org:8443/icon/firstfloor?state=null&format=PNG";
                                    itemList.add(new Item(label, icon));
                                }
                            }

                            mIbsAdapter=new IBSAdapter(MainActivity.this,itemList);
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
