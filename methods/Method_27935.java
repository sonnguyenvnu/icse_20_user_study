@Override public boolean onCallApi(int page,@Nullable String parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(CommitCommentsMvp.View::hideProgress);
    return false;
  }
  if (page == 1) {
    manageObservable(RestProvider.getRepoService(isEnterprise()).isCollaborator(login,repoId,Login.getUser().getLogin()).doOnNext(booleanResponse -> isCollaborator=booleanResponse.code() == 204));
  }
  setCurrentPage(page);
  makeRestCall(RestProvider.getRepoService(isEnterprise()).getCommitComments(login,repoId,sha,page).flatMap(listResponse -> {
    lastPage=listResponse.getLast();
    return TimelineModel.construct(listResponse.getItems());
  }
).doOnComplete(() -> {
    if (lastPage <= 1) {
      sendToView(CommitCommentsMvp.View::showReload);
    }
  }
),listResponse -> sendToView(view -> view.onNotifyAdapter(listResponse,page)));
  return true;
}
