@Override public boolean onCallApi(int page,@Nullable PullRequest parameter){
  if (parameter == null) {
    sendToView(BaseMvp.FAView::hideProgress);
    return false;
  }
  String login=parameter.getLogin();
  String repoId=parameter.getRepoId();
  int number=parameter.getNumber();
  if (page <= 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
    pages.clear();
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(PullRequestTimelineMvp.View::hideProgress);
    return false;
  }
  if (page == 1) {
    manageObservable(RestProvider.getRepoService(isEnterprise()).isCollaborator(login,repoId,Login.getUser().getLogin()).doOnNext(booleanResponse -> isCollaborator=booleanResponse.code() == 204));
  }
  setCurrentPage(page);
  if (parameter.getHead() != null) {
    Observable<List<TimelineModel>> observable=Observable.zip(RestProvider.getIssueService(isEnterprise()).getTimeline(login,repoId,number,page),RestProvider.getReviewService(isEnterprise()).getPrReviewComments(login,repoId,number),RestProvider.getPullRequestService(isEnterprise()).getPullStatus(login,repoId,parameter.getHead().getSha()).onErrorReturn(throwable -> RestProvider.getPullRequestService(isEnterprise()).getPullStatus(login,repoId,parameter.getBase().getSha()).blockingFirst(new PullRequestStatusModel())),(response,comments,status) -> {
      if (response != null) {
        lastPage=response.getLast();
        List<TimelineModel> models=TimelineConverter.INSTANCE.convert(response.getItems(),comments);
        if (page == 1 && status != null) {
          status.setMergable(parameter.isMergeable());
          status.setMergeableState(parameter.getMergeableState());
          if (status.getState() != null) {
            models.add(0,new TimelineModel(status));
          }
        }
        return models;
      }
 else {
        return Collections.emptyList();
      }
    }
);
    makeRestCall(observable,timeline -> sendToView(view -> view.onNotifyAdapter(timeline,page)));
    return true;
  }
  return false;
}
