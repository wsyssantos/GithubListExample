package com.example.wesley.githublist.pullrequestlist;

import com.example.wesley.githublist.base.BasePresenter;
import com.example.wesley.githublist.base.BaseView;
import com.example.wesley.githublist.model.PullRequest;
import com.example.wesley.githublist.model.Repository;

import java.util.List;

/**
 * Created by wesley on 9/15/16.
 */
public interface PullRequestListContract {

    interface View extends BaseView<Presenter> {
        void pullRequestListReceived(List<PullRequest> requestList);
    }

    interface Presenter extends BasePresenter {
        void loadPullRequestList(Repository repo);
    }
}
