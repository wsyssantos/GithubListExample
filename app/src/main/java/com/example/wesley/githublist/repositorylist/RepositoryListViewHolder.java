package com.example.wesley.githublist.repositorylist;

import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wesley.githublist.R;
import com.example.wesley.githublist.model.Repository;
import com.example.wesley.githublist.model.User;
import com.example.wesley.githublist.pullrequestlist.PullRequestListActivity;
import com.example.wesley.githublist.user.UserContract;
import com.example.wesley.githublist.user.UserPresenter;
import com.example.wesley.githublist.util.PicassoCache;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wesley on 9/16/16.
 */
public class RepositoryListViewHolder extends RecyclerView.ViewHolder implements UserContract.View {
    public static final String TAG = "RepositoryListHolder";
    private UserContract.Presenter presenter;

    @BindView(R.id.imgUserPicture)
    CircleImageView imgUserPicture;
    @BindView(R.id.txtUserLogin)
    AppCompatTextView txtUserLogin;
    @BindView(R.id.txtUserName)
    AppCompatTextView txtUserName;
    @BindView(R.id.txtRepositoryName)
    AppCompatTextView txtRepositoryName;
    @BindView(R.id.txtRepositoryDescription)
    AppCompatTextView txtRepositoryDescription;
    @BindView(R.id.txtStarCount)
    AppCompatTextView txtStarCount;
    @BindView(R.id.txtForkCount)
    AppCompatTextView txtForkCount;
    @BindView(R.id.cardViewRepositoryList)
    CardView cardViewRepositoryList;

    public RepositoryListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        setPresenter(new UserPresenter(this));
    }

    public void refreshData(Repository repository) {
        txtUserName.setVisibility(View.INVISIBLE);
        txtUserLogin.setText(repository.getOwner().getLogin());
        txtRepositoryName.setText(repository.getName());
        txtRepositoryDescription.setText(repository.getDescription());
        txtStarCount.setText(Integer.toString(repository.getStarCount()));
        txtForkCount.setText(Integer.toString(repository.getForksCount()));
        loadImagePicture(repository.getOwner().getAvatarUrl());
        presenter.gerUserByLogin(repository.getOwner().getLogin());
        cardViewClickConfig(repository);
    }

    private void loadImagePicture(final String url) {
        PicassoCache.getInstance(itemView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(imgUserPicture);
    }

    @Override
    public void userReceived(User user) {
        txtUserName.setText(user.getName());
        txtUserName.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() {  }

    @Override
    public void errorReceived(Throwable e) {
        Log.e(TAG, e.getLocalizedMessage());
    }

    public void cardViewClickConfig(final Repository repository) {
        cardViewRepositoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), PullRequestListActivity.class);
                intent.putExtra(PullRequestListActivity.INTENT_REQUEST_URL, repository);
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
