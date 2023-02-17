package com.it.model;

import java.util.Date;

public class Comment {
    private Integer num;

    private String username;

    private String comments;

    private Date time;

    private String position;

    private String imageid;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid == null ? null : imageid.trim();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "num=" + num +
                ", username='" + username + '\'' +
                ", comments='" + comments + '\'' +
                ", time=" + time +
                ", position='" + position + '\'' +
                ", imageid='" + imageid + '\'' +
                '}';
    }
}