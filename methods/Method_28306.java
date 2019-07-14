@Override public void onHandleComment(@NonNull String text,@Nullable Bundle bundle){
  if (getView() == null)   return;
  PullRequest pullRequest=getView().getPullRequest();
  if (pullRequest != null) {
    if (bundle == null) {
      CommentRequestModel commentRequestModel=new CommentRequestModel();
      commentRequestModel.setBody(text);
      manageDisposable(RxHelper.getObservable(RestProvider.getIssueService(isEnterprise()).createIssueComment(pullRequest.getLogin(),pullRequest.getRepoId(),pullRequest.getNumber(),commentRequestModel)).doOnSubscribe(disposable -> sendToView(view -> view.showBlockingProgress(0))).subscribe(comment -> sendToView(view -> view.addComment(TimelineModel.constructComment(comment))),throwable -> {
        onError(throwable);
        sendToView(PullRequestTimelineMvp.View::onHideBlockingProgress);
      }
));
    }
  }
}
