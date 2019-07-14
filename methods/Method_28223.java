@Override public void onWorkOffline(){
  if (commits.isEmpty()) {
    manageDisposable(RxHelper.getSingle(Commit.getCommits(repoId,login,number)).subscribe(models -> sendToView(view -> view.onNotifyAdapter(models,1))));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}
