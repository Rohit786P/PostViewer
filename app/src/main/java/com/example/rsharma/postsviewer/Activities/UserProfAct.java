package com.example.rsharma.postsviewer.Activities;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.rsharma.postsviewer.Adapters.MyExAdapter;
import com.example.rsharma.postsviewer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProfAct extends AppCompatActivity
{
    String urlInfo="https://jsonplaceholder.typicode.com/users";
    TextView tvName;
    TextView tvid;
    TextView tvFName;
    TextView tvMail;
    TextView tvPhone;
    TextView tvWbsite;
    TextView tvStreet;
    TextView tvSuite;
    TextView tvCity;
    TextView tvZp;
    TextView tvLong;
    TextView tvLat;
    TextView tvCname;
    TextView tvCp;
    TextView tvBs;
    ProgressDialog pgDialogue;
    RequestQueue requestQueue1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        //initialization of Text Boxes

        tvName=(TextView)findViewById(R.id.UserName);
        tvid=(TextView)findViewById(R.id.userIdDynamic);
        tvFName=(TextView)findViewById(R.id.userNameDynamic);
        tvMail=(TextView)findViewById(R.id.emailIdDynamic);
        tvPhone=(TextView)findViewById(R.id.phoneDynamic);
        tvWbsite=(TextView)findViewById(R.id.websiteDynamic);
        tvStreet=(TextView)findViewById(R.id.streetDynamic);
        tvSuite=(TextView)findViewById(R.id.suiteDynamic);
        tvCity=(TextView)findViewById(R.id.cityDynamic);
        tvZp=(TextView)findViewById(R.id.zipcodeDynamic);
        tvLong=(TextView)findViewById(R.id.geoLongtDynamic);
        tvLat=(TextView)findViewById(R.id.geoLatDynamic);
        tvCname=(TextView)findViewById(R.id.nameCDynamic);
        tvCp=(TextView)findViewById(R.id.catchPDynamic);
        tvBs=(TextView)findViewById(R.id.bsDynamic);


        //enabling Back button
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setLogo(R.mipmap.post1);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //initialization of ProgressDialogue
        pgDialogue=new ProgressDialog(UserProfAct.this);
        pgDialogue.setTitle("USER INFO :");
        pgDialogue.setMessage("please wait...");
        pgDialogue.setCancelable(false);
        pgDialogue.show();


        //here we are getting the respective position(user id) of the user using Intent object
        Intent intent1=getIntent();

        final int idNumber=intent1.getIntExtra("UserID",0);
        Log.e("Rashi",""+idNumber);
        readJSONForUserInfo(idNumber);

        readJSONForUserInfo(idNumber);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId=item.getItemId();
       if(itemId==android.R.id.home)
       {
           UserProfAct.this.finish();
       }
        return super.onOptionsItemSelected(item);
    }

    //Inside this method ,Volley is doing the job of JSON reading and fetching the records as well setting the TextViews according to it=
    public void readJSONForUserInfo(int idNum)
    {

        //here we are getting the respective user Information...whose post has been clicked
        // so for this ..first we have to read the JSON FILE that is containing the user Information
        //initializing RequestQueue Object
        final int idNum1=idNum;
        requestQueue1 = Volley.newRequestQueue(this);
        final JsonArrayRequest jsArReq2 = new JsonArrayRequest(urlInfo, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try {
                    JSONObject jsObj3=response.getJSONObject(idNum1);
                    //for internal Objects of Address
                    JSONObject jsonObjA=(JSONObject)jsObj3.get("address");
                    String street=jsonObjA.get("street").toString();
                    String suite=jsonObjA.get("suite").toString();
                    String city=jsonObjA.get("city").toString();
                    String zipcode=jsonObjA.get("zipcode").toString();

                    //for internal Objects of Geo
                    JSONObject jsonObjG=(JSONObject)jsonObjA.get("geo");
                    String lat=jsonObjG.get("lat").toString();
                    String lng=jsonObjG.get("lng").toString();

                   //for internal Objects of Address
                    JSONObject jsonObjC=(JSONObject)jsObj3.get("company");
                    String name=jsonObjC.get("name").toString();
                    String catchPhrase=jsonObjC.get("catchPhrase").toString();
                    String bs=jsonObjC.get("bs").toString();

                    if(pgDialogue!=null)
                    {
                        pgDialogue.dismiss();
                    }

                    //setting respective values to respective TextViews

                    tvName.setText(jsObj3.getString("username"));
                    tvid.setText(jsObj3.getString("id"));
                    tvFName.setText(jsObj3.getString("name"));
                    tvMail.setText(jsObj3.getString("email"));
                    tvPhone.setText(jsObj3.getString("phone"));
                    tvWbsite.setText(jsObj3.getString("website"));
                    tvStreet.setText(street);
                    tvSuite.setText(suite);
                    tvCity.setText(city);
                    tvZp.setText(zipcode);
                    tvLong.setText(lng);
                    tvLat.setText(lat);
                    tvCname.setText(name);
                    tvCp.setText(catchPhrase);
                    tvBs.setText(bs);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();

            }
        });
        requestQueue1.add(jsArReq2);

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        UserProfAct.this.finish();
    }

}

