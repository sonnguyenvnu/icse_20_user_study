@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(PullRequestFilesMvp.View::hideProgress);
    return false;
  }
  if (repoId == null || login == null)   return false;
  makeRestCall(RestProvider.getPullRequestService(isEnterprise()).getPullRequestFiles(login,repoId,number,page).flatMap(commitFileModelPageable -> {
    if (commitFileModelPageable != null) {
      lastPage=commitFileModelPageable.getLast();
      if (commitFileModelPageable.getItems() != null) {
        return Observable.just(CommitFileChanges.construct(commitFileModelPageable.getItems()));
      }
    }
    return Observable.empty();
  }
),response -> sendToView(view -> view.onNotifyAdapter(response,page)));
  return true;
}
