package com.example.rsharma.postsviewer.Models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by RSharma on 1/4/2017.
 */

public class PostParent implements ParentObject
{
    private List<Object> mChildList;
    private int iD;
    private String title;
    private String msg;


    //constructor
    public PostParent(String title,String msg,int iD)
    {

        this.title=title;
        this.msg=msg;
        this.iD=iD;
    }

    //getters for private variables;


    public int getiD()
    {
        return iD;
    }

    public String getTitle()
    {
        return title;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public List<Object> getChildObjectList()
    {
        return mChildList;
    }

    @Override
    public void setChildObjectList(List<Object> list)
    {
        mChildList=list;

    }

}
