@Override public void onUpdateTimeline(){
  IssueTimelineFragment issueDetailsView=getIssueTimelineFragment();
  if (issueDetailsView != null && getPresenter().getIssue() != null) {
    issueDetailsView.onRefresh();
  }
}
