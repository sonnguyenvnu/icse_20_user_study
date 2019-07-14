@Override public boolean onCallApi(int page,@Nullable String parameter){
  if (page == 1 || parameter == null) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0 || parameter == null) {
    sendToView(FilterIssuesMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  makeRestCall(RestProvider.getSearchService(isEnterprise()).searchIssues(parameter,page),issues -> {
    lastPage=issues.getLast();
    if (getCurrentPage() == 1) {
      sendToView(view -> view.onSetCount(issues.getTotalCount()));
    }
    sendToView(view -> view.onNotifyAdapter(issues.getItems(),page));
  }
);
  return true;
}
