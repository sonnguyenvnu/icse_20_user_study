@Override public void onUpdateCount(int totalCount){
  if (tabsBadgeListener != null)   tabsBadgeListener.onSetBadge(getPresenter().getIssueState() == IssueState.open ? 0 : 1,totalCount);
}
