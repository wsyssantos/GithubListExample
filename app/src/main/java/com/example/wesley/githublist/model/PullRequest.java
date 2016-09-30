package com.example.wesley.githublist.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by wesley on 9/15/16.
 */
public class PullRequest {
    private int id;
    private String title;
    private String body;
    @SerializedName("created_at")
    private Date createdAt;
    private User user;
    @SerializedName("state")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PullRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", status='" + status + '\'' +
                '}';
    }
}
