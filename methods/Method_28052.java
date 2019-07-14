@Override public void onWorkOffline(){
  if (releases.isEmpty()) {
    manageDisposable(RxHelper.getSingle(Release.get(repoId,login)).subscribe(releasesModels -> sendToView(view -> view.onNotifyAdapter(releasesModels,1))));
  }
 else {
    sendToView(RepoReleasesMvp.View::hideProgress);
  }
}
