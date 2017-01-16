package com.example.rsharma.postsviewer.ViewHolders;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.rsharma.postsviewer.R;

/**
 * Created by RSharma on 1/4/2017.
 */

public class PVHolder extends ParentViewHolder
{
    public TextView titleTv;
    public TextView bodyTv;
    public TextView _titleATv;
    public TextView _bodyATv;
    public ImageView _arrowImg;
    public View _view1;
    public View _view2;


    public PVHolder(View itemView)
    {
        super(itemView);
        titleTv=(TextView)itemView.findViewById(R.id.Title);
        bodyTv=(TextView)itemView.findViewById(R.id.Body);
        _titleATv=(TextView)itemView.findViewById(R.id.postTitle);
        _bodyATv=(TextView)itemView.findViewById(R.id.postBody);
        _arrowImg=(ImageView)itemView.findViewById(R.id.expandArrow);
        _view1=(View)itemView.findViewById(R.id.rellayout01);

    }

}
