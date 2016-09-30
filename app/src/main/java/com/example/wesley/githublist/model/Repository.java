package com.example.wesley.githublist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wesley on 9/15/16.
 */
public class Repository implements Serializable {

    private int id;
    private String name;
    private String description;
    private String url;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("forks_count")
    private int forksCount;
    @SerializedName("stargazers_count")
    private int starCount;
    private User owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", forksCount=" + forksCount +
                ", starCount=" + starCount +
                ", owner=" + owner +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Repository that = (Repository) o;

        if (id != that.id) return false;
        if (forksCount != that.forksCount) return false;
        if (starCount != that.starCount) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (htmlUrl != null ? !htmlUrl.equals(that.htmlUrl) : that.htmlUrl != null) return false;
        return owner != null ? owner.equals(that.owner) : that.owner == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (htmlUrl != null ? htmlUrl.hashCode() : 0);
        result = 31 * result + forksCount;
        result = 31 * result + starCount;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
