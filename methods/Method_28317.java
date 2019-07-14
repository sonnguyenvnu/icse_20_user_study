@Override public boolean onCallApi(int page,@Nullable IssueState parameter){
  if (parameter == null) {
    sendToView(RepoPullRequestMvp.View::hideProgress);
    return false;
  }
  this.issueState=parameter;
  if (page == 1) {
    onCallCountApi(issueState);
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(RepoPullRequestMvp.View::hideProgress);
    return false;
  }
  if (repoId == null || login == null)   return false;
  makeRestCall(RestProvider.getPullRequestService(isEnterprise()).getPullRequests(login,repoId,parameter.name(),page),response -> {
    lastPage=response.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(PullRequest.save(response.getItems(),login,repoId));
    }
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
);
  return true;
}
