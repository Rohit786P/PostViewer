package com.example.rsharma.postsviewer.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.rsharma.postsviewer.R;

/**
 * Created by RSharma on 1/4/2017.
 */

public class CVHolder extends ChildViewHolder
{
    public TextView titleCTv;
    public TextView bodyCTv;
    public TextView _titleACTv;
    public TextView _bodyACTv;



    public CVHolder(View itemView)
    {
        super(itemView);
        titleCTv =(TextView)itemView.findViewById(R.id.titleChild);
        bodyCTv=(TextView)itemView.findViewById(R.id.titleChild);
        _titleACTv=(TextView)itemView.findViewById(R.id.postChildTitle);
        _bodyACTv=(TextView)itemView.findViewById(R.id.postChildBody);



    }
}
