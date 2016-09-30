package com.example.wesley.githublist.repositorylist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wesley.githublist.R;
import com.example.wesley.githublist.model.Repository;

import java.util.List;

/**
 * Created by wesley on 9/16/16.
 */
public class RepositoryListAdapter extends RecyclerView.Adapter<RepositoryListViewHolder> {

    private List<Repository> repositoryList;

    public RepositoryListAdapter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    @Override
    public RepositoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_list_item, parent, false);
        RepositoryListViewHolder viewHolder = new RepositoryListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryListViewHolder holder, int position) {
        holder.refreshData(this.repositoryList.get(position));
    }

    public void addNewElements(List<Repository> newElements) {
        this.repositoryList.addAll(newElements);
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.repositoryList.size();
    }
}
