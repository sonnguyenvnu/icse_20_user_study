@Override public boolean onCallApi(int page,@Nullable Issue parameter){
  if (parameter == null) {
    sendToView(BaseMvp.FAView::hideProgress);
    return false;
  }
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(IssueTimelineMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  String login=parameter.getLogin();
  String repoId=parameter.getRepoId();
  if (page == 1) {
    manageObservable(RestProvider.getRepoService(isEnterprise()).isCollaborator(login,repoId,Login.getUser().getLogin()).doOnNext(booleanResponse -> isCollaborator=booleanResponse.code() == 204));
  }
  int number=parameter.getNumber();
  Observable<List<TimelineModel>> observable=RestProvider.getIssueService(isEnterprise()).getTimeline(login,repoId,number,page).flatMap(response -> {
    if (response != null) {
      lastPage=response.getLast();
    }
    return TimelineConverter.INSTANCE.convert(response != null ? response.getItems() : null);
  }
).toList().toObservable();
  makeRestCall(observable,timeline -> {
    sendToView(view -> view.onNotifyAdapter(timeline,page));
    loadComment(page,commentId,login,repoId,timeline);
  }
);
  return true;
}
