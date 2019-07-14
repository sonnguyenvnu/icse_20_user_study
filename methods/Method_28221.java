@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(PullRequestCommitsMvp.View::hideProgress);
    return false;
  }
  if (repoId == null || login == null)   return false;
  makeRestCall(RestProvider.getPullRequestService(isEnterprise()).getPullRequestCommits(login,repoId,number,page),response -> {
    lastPage=response.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(Commit.save(response.getItems(),repoId,login,number));
    }
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
);
  return true;
}
