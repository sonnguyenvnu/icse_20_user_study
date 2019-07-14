@Override public void onWorkOffline(){
  if (issues.isEmpty()) {
    manageDisposable(RxHelper.getSingle(Issue.getIssues(repoId,login,issueState)).subscribe(issueModel -> sendToView(view -> {
      view.onNotifyAdapter(issueModel,1);
      view.onUpdateCount(issueModel.size());
    }
)));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}
