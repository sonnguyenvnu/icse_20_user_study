@Override public void onWorkOffline(){
  if (pullRequests.isEmpty()) {
    manageDisposable(RxHelper.getSingle(PullRequest.getPullRequests(repoId,login,issueState)).subscribe(pulls -> sendToView(view -> {
      view.onNotifyAdapter(pulls,1);
      view.onUpdateCount(pulls.size());
    }
)));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}
