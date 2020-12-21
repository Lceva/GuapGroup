package com.example.guapgroup;

import java.util.Date;

public class MassageArea {
    private String massageText;
    private String massageUser;
    private long messageTime;

    public MassageArea(String massageText, String massageUser){
        this.massageText= massageText;
        this.massageUser= massageUser;
        Date date = new Date();
        messageTime = date.getTime();
    }

    public String getMassageText(){
        return massageText;
    }
    public void setMassageText(String massageText) {
        this.massageText = massageText;
    }

    public String getMassageUser(){
        return massageUser;
    }

    public void setMassageUser(String massageUser) {
        this.massageUser = massageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
