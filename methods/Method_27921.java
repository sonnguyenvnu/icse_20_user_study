@Override public void onWorkOffline(@NonNull String login){
  if (repos.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Repo.getStarred(login).toObservable()).subscribe(repoModels -> sendToView(view -> {
      starredCount=-1;
      view.onUpdateCount(repoModels != null ? repoModels.size() : 0);
      view.onNotifyAdapter(repoModels,1);
    }
)));
  }
 else {
    sendToView(ProfileStarredMvp.View::hideProgress);
  }
}
