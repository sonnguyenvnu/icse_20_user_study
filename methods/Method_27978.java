@Override public void onWorkOffline(){
  if (commits.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Commit.getCommits(repoId,login).toObservable()).subscribe(models -> sendToView(view -> view.onNotifyAdapter(models,1))));
  }
 else {
    sendToView(BaseMvp.FAView::hideProgress);
  }
}
