@Override public void onRefresh(boolean isLastUpdated){
  getPresenter().onSetSortBy(isLastUpdated);
  getPresenter().onCallApi(1,IssueState.closed);
}
