package com.example.wesley.githublist.repositorylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wesley.githublist.R;
import com.example.wesley.githublist.base.BaseActivity;
import com.example.wesley.githublist.customview.EndlessRecyclerViewScrollListener;
import com.example.wesley.githublist.model.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wesley on 9/16/16.
 */
public class RepositoryListActivity extends BaseActivity implements RepositoryListContract.View {

    @BindView(R.id.listRecyclerView)
    RecyclerView repositoryListRecyclerView;

    private LinearLayoutManager layoutManager;
    private RepositoryListAdapter repositoryListAdapter;
    private RepositoryListContract.Presenter presenter;
    private int currentPage = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_list_act);
        ButterKnife.bind(this);

        layoutManager = new LinearLayoutManager(this);
        repositoryListRecyclerView.setLayoutManager(layoutManager);
        repositoryListRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                presenter.loadRepositoriesList(++currentPage);
            }
        });

        setPresenter(new RepositoryListPresenter(this));
        presenter.loadRepositoriesList(currentPage);

        configureActionBar();
    }

    private void configureActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.repo_list_title));
    }

    @Override
    public void repositoriesReceived(List<Repository> repositoryList) {
        repositoryListAdapter = (RepositoryListAdapter) repositoryListRecyclerView.getAdapter();
        if(repositoryListAdapter == null) {
            repositoryListAdapter = new RepositoryListAdapter(repositoryList);
            repositoryListRecyclerView.setAdapter(repositoryListAdapter);
        } else {
            repositoryListAdapter.addNewElements(repositoryList);
        }

    }

    @Override
    public void setPresenter(RepositoryListContract.Presenter presenter) {
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
