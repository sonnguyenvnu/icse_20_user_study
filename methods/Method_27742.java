@Override public boolean onCallApi(int page,@Nullable IssueState parameter){
  if (parameter == null) {
    throw new NullPointerException("Parameter is null");
  }
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(MyPullRequestsMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  makeRestCall(RestProvider.getPullRequestService(isEnterprise()).getPullsWithCount(getUrl(parameter),page),response -> {
    lastPage=response.getLast();
    if (getCurrentPage() == 1) {
      sendToView(view -> view.onSetCount(response.getTotalCount()));
    }
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
);
  return true;
}
