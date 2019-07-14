@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(RepoCommitsMvp.View::hideProgress);
    return false;
  }
  if (repoId == null || login == null)   return false;
  Observable<Pageable<Commit>> observable=InputHelper.isEmpty(path) ? RestProvider.getRepoService(isEnterprise()).getCommits(login,repoId,branch,page) : RestProvider.getRepoService(isEnterprise()).getCommits(login,repoId,branch,path,page);
  makeRestCall(observable,response -> {
    if (response != null && response.getItems() != null) {
      lastPage=response.getLast();
      if (getCurrentPage() == 1) {
        manageDisposable(Commit.save(response.getItems(),repoId,login));
      }
    }
    sendToView(view -> view.onNotifyAdapter(response != null ? response.getItems() : null,page));
  }
);
  return true;
}
