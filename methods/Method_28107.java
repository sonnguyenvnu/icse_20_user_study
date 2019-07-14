@Override public boolean onCallApi(int page,@RepoMiscMVp.MiscType @Nullable Integer parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(RepoMiscMVp.View::hideProgress);
    return false;
  }
switch (type) {
case RepoMiscMVp.WATCHERS:
    makeRestCall(RestProvider.getRepoService(isEnterprise()).getWatchers(owner,repo,page),response -> onResponse(page,response));
  return true;
case RepoMiscMVp.STARS:
makeRestCall(RestProvider.getRepoService(isEnterprise()).getStargazers(owner,repo,page),response -> onResponse(page,response));
return true;
case RepoMiscMVp.FORKS:
makeRestCall(RestProvider.getRepoService(isEnterprise()).getForks(owner,repo,page).flatMap(repoPageable -> {
lastPage=repoPageable.getLast();
return Observable.fromIterable(repoPageable.getItems()).map(Repo::getOwner).toList().toObservable();
}
),owners -> sendToView(view -> view.onNotifyAdapter(owners,page)));
return true;
}
return false;
}
