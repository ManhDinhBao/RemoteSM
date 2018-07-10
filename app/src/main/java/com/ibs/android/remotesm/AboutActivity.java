package com.ibs.android.remotesm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class AboutActivity extends AppCompatActivity {
    CardView cardContact;
    TextView txtPhoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");
        cardContact=(CardView)findViewById(R.id.card_Contact);
        txtPhoneNo=(TextView)findViewById(R.id.txtPhoneNumber);

        cardContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Intent.ACTION_DIAL,Uri.fromParts("tel",txtPhoneNo.getText().toString(),null));
                //intent.setData(Uri.parse("0977822336"));
                //startActivity(intent);

                PutJson();
            }
        });
    }

   private void PutJson()
   {
       try
       {
           RequestQueue requestQueue = Volley.newRequestQueue(this);
           JSONObject jsonBody = new JSONObject();
           jsonBody.put("state","OFF");
            
           final String mRequestBody = jsonBody.toString();
           String puturl="http://demo.openhab.org:8080/rest/items/LivingRoom_Light3/state";
           StringRequest stringRequest = new StringRequest(Request.Method.PUT, puturl, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   Log.i("LOG_VOLLEY", response);
               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Log.e("LOG_VOLLEY", error.toString());
               }
           }) {
               @Override
               public String getBodyContentType() {
                   return "application/json; charset=utf-8";
               }

               @Override
               public byte[] getBody() throws AuthFailureError {
                   try {
                       return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                   } catch (UnsupportedEncodingException uee) {
                       VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                       return null;
                   }
               }

               @Override
               protected Response<String> parseNetworkResponse(NetworkResponse response) {
                   String responseString = "";
                   if (response != null) {

                       responseString = String.valueOf(response.statusCode);

                   }
                   return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
               }
           };

           requestQueue.add(stringRequest);
       }
       catch (JSONException e)
       {
           e.printStackTrace();
       }
   }
}
