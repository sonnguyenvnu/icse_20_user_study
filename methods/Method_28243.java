@Override public void onUpdateTimeline(){
  supportInvalidateOptionsMenu();
  PullRequestTimelineFragment pullRequestDetailsView=getPullRequestTimelineFragment();
  if (pullRequestDetailsView != null && getPresenter().getPullRequest() != null) {
    pullRequestDetailsView.onRefresh();
  }
}
