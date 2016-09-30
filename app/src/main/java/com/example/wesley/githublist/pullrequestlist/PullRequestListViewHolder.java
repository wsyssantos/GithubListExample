package com.example.wesley.githublist.pullrequestlist;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.wesley.githublist.R;
import com.example.wesley.githublist.model.PullRequest;
import com.example.wesley.githublist.model.User;
import com.example.wesley.githublist.user.UserContract;
import com.example.wesley.githublist.user.UserPresenter;
import com.example.wesley.githublist.util.PicassoCache;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by wesley on 9/16/16.
 */
public class PullRequestListViewHolder extends RecyclerView.ViewHolder implements UserContract.View {
    public static final String TAG = "PullRequestListHolder";
    private UserContract.Presenter presenter;

    @BindView(R.id.txtRequestTitle)
    AppCompatTextView txtRequestTitle;
    @BindView(R.id.txtRequestBody)
    AppCompatTextView txtRequestBody;
    @BindView(R.id.txtRequestDate)
    AppCompatTextView txtRequestDate;
    @BindView(R.id.txtRequestState)
    AppCompatTextView txtRequestState;
    @BindView(R.id.txtUserLogin)
    AppCompatTextView txtUserLogin;
    @BindView(R.id.txtUserName)
    AppCompatTextView txtUserName;
    @BindView(R.id.imgUserPicture)
    CircleImageView imgUserPicture;
    @BindView(R.id.containerUserData)
    RelativeLayout containerUserData;

    public PullRequestListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        setPresenter(new UserPresenter(this));
    }

    @Override
    public void userReceived(User user) {
        txtUserName.setText(user.getName());
        txtUserName.setVisibility(View.VISIBLE);
    }

    public void refreshData(PullRequest pullRequest) {
        txtRequestTitle.setText(pullRequest.getTitle());
        if(!TextUtils.isEmpty(pullRequest.getBody())) {
            txtRequestBody.setText(pullRequest.getBody());
        } else {
            txtRequestBody.setText(itemView.getResources().getString(R.string.empty_request_body));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        txtRequestDate.setText(sdf.format(pullRequest.getCreatedAt()));
        txtUserLogin.setText(pullRequest.getUser().getLogin());
        presenter.gerUserByLogin(pullRequest.getUser().getLogin());
        configureStatusView(pullRequest);
        loadImagePicture(pullRequest.getUser().getAvatarUrl());
    }

    private void configureStatusView(PullRequest pullRequest) {
        if(pullRequest.getStatus().equals("open")) {
            containerUserData.setBackgroundColor(itemView.getResources().getColor(R.color.requestListOpenColor));
            txtRequestState.setText(itemView.getResources().getString(R.string.request_open_title));
        } else {
            containerUserData.setBackgroundColor(itemView.getResources().getColor(R.color.requestListClosedColor));
            txtRequestState.setText(itemView.getResources().getString(R.string.request_closed_title));
        }
    }

    private void loadImagePicture(final String url) {
        PicassoCache.getInstance(imgUserPicture.getContext())
                .load(url)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(imgUserPicture);
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
}
