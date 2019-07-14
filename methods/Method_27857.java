@Override public void onWorkOffline(@NonNull String login){
  if (repos.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Repo.getMyRepos(login).toObservable()).subscribe(repoModels -> sendToView(view -> view.onNotifyAdapter(repoModels,1))));
  }
 else {
    sendToView(OrgReposMvp.View::hideProgress);
  }
}
