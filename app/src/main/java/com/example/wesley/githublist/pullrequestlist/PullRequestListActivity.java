package com.example.wesley.githublist.pullrequestlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wesley.githublist.R;
import com.example.wesley.githublist.base.BaseActivity;
import com.example.wesley.githublist.model.PullRequest;
import com.example.wesley.githublist.model.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/16/16.
 */
public class PullRequestListActivity extends BaseActivity implements PullRequestListContract.View {
    public static final String INTENT_REQUEST_URL = "REQUEST_URL";

    private PullRequestListContract.Presenter presenter;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.listRecyclerView)
    RecyclerView listRecyclerView;
    @BindView(R.id.imgNoResult)
    ImageView imgNoResult;
    @BindView(R.id.emptyResultMessage)
    RelativeLayout emptyResultMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_request_list_act);
        ButterKnife.bind(this);

        configureActionBar();

        layoutManager = new LinearLayoutManager(this);
        listRecyclerView.setLayoutManager(layoutManager);

        Intent intent = getIntent();
        Repository repo = (Repository) intent.getSerializableExtra(INTENT_REQUEST_URL);

        setPresenter(new PullRequestListPresenter(this));

        if (repo != null)
            presenter.loadPullRequestList(repo);
    }

    @Override
    public void pullRequestListReceived(List<PullRequest> requestList) {
        if(requestList != null && requestList.size() > 0) {
            PullRequestListAdapter repositoryListAdapter = new PullRequestListAdapter(requestList);
            listRecyclerView.setAdapter(repositoryListAdapter);
            emptyResultMessage.setVisibility(View.INVISIBLE);
            listRecyclerView.setVisibility(View.VISIBLE);
        } else {
            listRecyclerView.setVisibility(View.INVISIBLE);
            Animation growAnim = AnimationUtils.loadAnimation(this, R.anim.grow_center_anim);
            imgNoResult.startAnimation(growAnim);
            emptyResultMessage.setVisibility(View.VISIBLE);
        }
    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.request_list_title));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setPresenter(PullRequestListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        showDialog();
    }

    @Override
    public void hideLoading() {
        hideDialog();
    }

    @Override
    public void errorReceived(Throwable e) {
        showErrorDialog(e);
    }
}