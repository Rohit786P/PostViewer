package com.example.rsharma.postsviewer.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.rsharma.postsviewer.Activities.UserProfAct;
import com.example.rsharma.postsviewer.Models.PostChild;
import com.example.rsharma.postsviewer.Models.PostParent;
import com.example.rsharma.postsviewer.R;
import com.example.rsharma.postsviewer.ViewHolders.CVHolder;
import com.example.rsharma.postsviewer.ViewHolders.PVHolder;

import java.util.List;

/**
 * Created by RSharma on 1/4/2017.
 */

public class MyExAdapter extends ExpandableRecyclerAdapter<PVHolder, CVHolder> {
    LayoutInflater inflator;
    Context context;
    List<ParentObject> parentItemList;
    ProgressDialog progress1;

    //Constructor


    public MyExAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflator = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public PVHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflator.inflate(R.layout.post_row, viewGroup, false);
        return new PVHolder(view);
    }

    @Override
    public CVHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflator.inflate(R.layout.child_layout, viewGroup, false);
        return new CVHolder(view);
    }

    @Override
    public void onBindParentViewHolder(PVHolder pvHolder, int i, Object o)
    {
        final int position=i;
        PostParent pTitle = (PostParent) o;
        pvHolder._titleATv.setText(pTitle.getTitle());
        pvHolder._bodyATv.setText(pTitle.getMsg());
        pvHolder._view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                //opening activity
                openUserActivity(context,position,progress1);

            }
        });
    }

    @Override
    public void onBindChildViewHolder(CVHolder cvHolder, int i, Object o)
    {


        PostChild cTitle = (PostChild) o;
        cvHolder._titleACTv.setText(cTitle.getTitleC());
        cvHolder._bodyACTv.setText(cTitle.getMsgC());

    }

    public static void openUserActivity(Context context,int position,ProgressDialog progress1) {
        Intent intent = new Intent(context, UserProfAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("UserID",position);
        context.startActivity(intent);



    }


}
