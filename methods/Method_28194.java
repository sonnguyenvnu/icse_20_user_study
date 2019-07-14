@Override public void onHandleComment(@NonNull String text,@Nullable Bundle bundle){
  if (getView() == null)   return;
  Issue issue=getView().getIssue();
  if (issue != null) {
    if (bundle == null) {
      CommentRequestModel commentRequestModel=new CommentRequestModel();
      commentRequestModel.setBody(text);
      manageDisposable(RxHelper.getObservable(RestProvider.getIssueService(isEnterprise()).createIssueComment(issue.getLogin(),issue.getRepoId(),issue.getNumber(),commentRequestModel)).doOnSubscribe(disposable -> sendToView(view -> view.showBlockingProgress(0))).subscribe(comment -> sendToView(view -> view.addNewComment(TimelineModel.constructComment(comment))),throwable -> {
        onError(throwable);
        sendToView(IssueTimelineMvp.View::onHideBlockingProgress);
      }
));
    }
  }
}
