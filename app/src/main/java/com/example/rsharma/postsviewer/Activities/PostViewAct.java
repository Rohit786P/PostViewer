package com.example.rsharma.postsviewer.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.rsharma.postsviewer.Adapters.MyExAdapter;
import com.example.rsharma.postsviewer.Models.PostChild;
import com.example.rsharma.postsviewer.Models.PostParent;
import com.example.rsharma.postsviewer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostViewAct extends AppCompatActivity
{
    RecyclerView mRecyclerView;
    MyExAdapter adapter;
    RequestQueue rQueue;
    int count;
    static ArrayList<PostParent> postParents1 = new ArrayList<PostParent>();
    List<PostParent> a1 = new ArrayList<PostParent>();
    ArrayList<Integer> aInteger=new ArrayList<Integer>();
    int initial=0;
    public static ArrayList<PostParent> postParents = new ArrayList<PostParent>();
    boolean chkValue=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        rQueue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setLogo(R.mipmap.post1);
        actionBar.setDisplayUseLogoEnabled(true);
        mRecyclerView=(RecyclerView)findViewById(R.id.myRecView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        a1.clear();
        aInteger.clear();
        postParents.clear();


        readJson();

    }

/*
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        ((MyExAdapter)mRecyclerView.getAdapter()).onSaveInstanceState(outState);

    }*/
    //Volley Work here

    private void readJson() {
        rQueue = Volley.newRequestQueue(this);


        final JsonArrayRequest jsArReq = new JsonArrayRequest("https://jsonplaceholder.typicode.com/posts", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response)
            {
               // 1.HERE, first we are finding the maximum of the users i.e the Highest number of users ...so that
                //we will have the knowledge of Total number of users
                for(int p=0;p<response.length();p++)
                {

                    try
                    {
                        JSONObject jsObjUsers=response.getJSONObject(p);
                        int userIdForMax=Integer.parseInt(jsObjUsers.getString("userId"));

                        aInteger.add(userIdForMax);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
               Object noOfUsers=Collections.max(aInteger);
                int maxUsers=(Integer)noOfUsers;
                //here we have got the Total number of users according to JSON File...now we have to get their corresponding single post along with its Title

                for (int i = 1; i <=maxUsers; i++)
                {
                    count=0;
                    chkValue=false;


                    for (int j = 0; j < response.length(); j++)
                    {
                        if((chkValue == false )&& initial==0 )
                        {
                            count=0;
                        }
                        else
                        {
                            count=count+1;
                        }
                        /*


                        try {
                            JSONObject jsonObject1 = response.getJSONObject(j);
                            int userId = Integer.parseInt(jsonObject1.getString("userId"));
                            if (userId == i) {
                                PostParent pp1 = new PostParent(jsonObject1.getString("title"), jsonObject1.getString("body"));
                                a1.add(pp1);
                            } else {
                                PostParent pp2 = new PostParent(a1.get(0).getTitle().toString(), a1.get(0).getMsg().toString());
                                postParents.add(pp2);
                                break;
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
*/
                        //getting specific user'Post and respective Title
                        try {
                            JSONObject jsobj1=response.getJSONObject(j);
                            int userId=Integer.parseInt(jsobj1.getString("userId"));
                            if(userId==i && count==0)
                            {
                                PostParent pp = new PostParent(jsobj1.getString("title"),jsobj1.getString("body"),Integer.parseInt(jsobj1.getString("userId")));
                                postParents.add(pp);
                                chkValue=true;

                            }
                            else if(userId==i && !(count==0))
                            {
                                count++;
                            }
                            else if(!(userId==i) && count==0)
                            {
                                count++;
                            }
                            else if(!(userId==i) && !(count==0))
                            {
                                count++;
                            }
                            else
                            {
                                initial++;
                                break;
                            }


                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                }
                List<PostParent> titles=new ArrayList<PostParent>();
                titles.addAll(postParents);
                List<ParentObject> parentObject=new ArrayList<>();
                for(PostParent pstPr:titles)
                {
                    List<Object> childList=new ArrayList<>();
                    //here we are trying to fetch all the post for a specific to a user and bind those post as child View to that parent user
                    for(int m=0;m<response.length();m++)
                    {
                        try
                        {
                            JSONObject jsObj2=response.getJSONObject(m);
                            int resUserId=Integer.parseInt(jsObj2.getString("userId"));
                            if(resUserId==pstPr.getiD())
                            {
                                childList.add(new PostChild(jsObj2.getString("title"),jsObj2.getString("body")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    pstPr.setChildObjectList(childList);
                    parentObject.add(pstPr);
                    /*
                    childList.add(new PostChild("My NAME IS ANTHONY","i was in india since from 44444 and would not like to go back to my country"));
                    pstPr.setChildObjectList(childList);
                    parentObject.add(pstPr);
                    */

                }
                //Progress Bar Functioning
                final ProgressBar pbar = (ProgressBar) findViewById(R.id.progress); // Final so we can access it from the other thread
                pbar.setVisibility(View.GONE);
                pbar.setMax(5000);
                callingAdapter(parentObject);


                //sendArrayList(postParents);


                //     final ArrayList<PostParent>  postParents1=new ArrayList<PostParent>()


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();

            }
        });
        rQueue.add(jsArReq);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //here we have to inflate the menu
        getMenuInflater().inflate(R.menu.menu_messg,menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.logout)
        {
            SharedPreferences preferences = getSharedPreferences(LoginActivity.MyPref, 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            PostViewAct.this.finish();
            Intent intent = new Intent(PostViewAct.this, LoginActivity.class);
            this.startActivity(intent);
        }
        if(id==android.R.id.home)
        {
            PostViewAct.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        PostViewAct.this.finish();
    }

    //method callingAdapter
    private void callingAdapter(List<ParentObject> postParents)
    {
        adapter =new MyExAdapter(this,postParents);
        adapter.setCustomParentAnimationViewId(R.id.expandArrow);
        adapter.setParentClickableViewAnimationDefaultDuration();
      //  adapter.setParentAndIconExpandOnClick(false);
        mRecyclerView.setAdapter(adapter);
    }


}
