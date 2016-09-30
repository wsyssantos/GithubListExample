package com.example.wesley.githublist.api;

import com.example.wesley.githublist.model.RepositoryListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wesley on 9/15/16.
 */
public interface RepositoryApi {
    @GET("search/repositories?q=language:Java&sort=stars&client_id=d6cd5b8f71649b8d26df&client_secret=7e9120f010c6a0a39c633b0611e0c8b5e017b0bb")
    Observable<RepositoryListResponse> getPageRepository(@Query("page") int pageNumber);
}
