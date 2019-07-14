@Override public boolean onCallApi(int page,@Nullable IssueState parameter){
  if (parameter == null) {
    throw new NullPointerException("parameter is null");
  }
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(MyIssuesMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  makeRestCall(RestProvider.getIssueService(isEnterprise()).getIssuesWithCount(getUrl(parameter),page),issues -> {
    lastPage=issues.getLast();
    if (getCurrentPage() == 1) {
      sendToView(view -> view.onSetCount(issues.getTotalCount()));
    }
    sendToView(view -> view.onNotifyAdapter(issues.getItems(),page));
  }
);
  return true;
}
