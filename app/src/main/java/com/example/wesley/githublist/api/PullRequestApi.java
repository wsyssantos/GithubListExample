package com.example.wesley.githublist.api;

import com.example.wesley.githublist.model.PullRequest;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wesley on 9/15/16.
 */
public interface PullRequestApi {
    @GET("repos/{login}/{repositoryName}/pulls?client_id=d6cd5b8f71649b8d26df&client_secret=7e9120f010c6a0a39c633b0611e0c8b5e017b0bb")
    Observable<List<PullRequest>> getRepositoryPullRequests(@Path("login") String login, @Path("repositoryName") String repositoryName);
}
