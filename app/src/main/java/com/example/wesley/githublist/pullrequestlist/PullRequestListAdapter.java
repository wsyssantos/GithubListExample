package com.example.wesley.githublist.pullrequestlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.githublist.R;
import com.example.wesley.githublist.model.PullRequest;

import java.util.List;

/**
 * Created by wesley on 9/16/16.
 */
public class PullRequestListAdapter extends RecyclerView.Adapter<PullRequestListViewHolder> {

    private List<PullRequest> pullRequests;

    public PullRequestListAdapter(List<PullRequest> pullRequests) {
        this.pullRequests = pullRequests;
    }

    @Override
    public PullRequestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pull_request_list_item, parent, false);
        PullRequestListViewHolder viewHolder = new PullRequestListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PullRequestListViewHolder holder, int position) {
        holder.refreshData(this.pullRequests.get(position));
    }

    @Override
    public int getItemCount() {
        return pullRequests.size();
    }
}
