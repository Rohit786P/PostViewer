package com.example.rsharma.postsviewer.Models;

/**
 * Created by RSharma on 1/4/2017.
 */

public class PostChild
{
    public String titleC;
    public String msgC;

    //constructor

    public PostChild(String titleC, String msgC)
    {
        this.titleC = titleC;
        this.msgC = msgC;
    }


    //Getters and setters


    public String getTitleC() {
        return titleC;
    }

    public void setTitleC(String titleC) {
        this.titleC = titleC;
    }

    public String getMsgC() {
        return msgC;
    }

    public void setMsgC(String msgC) {
        this.msgC = msgC;
    }
}
