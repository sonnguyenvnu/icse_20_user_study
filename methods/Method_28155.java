@Override public void onWorkOffline(long issueNumber,@NonNull String repoId,@NonNull String login){
  if (issueModel == null) {
    manageDisposable(RxHelper.getObservable(Issue.getIssueByNumber((int)issueNumber,repoId,login)).subscribe(issueModel1 -> {
      if (issueModel1 != null) {
        issueModel=issueModel1;
        sendToView(view -> view.onSetupIssue(false));
      }
    }
));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}
