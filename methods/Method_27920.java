@Override public boolean onCallApi(int page,@Nullable String parameter){
  if (parameter == null) {
    throw new NullPointerException("Username is null");
  }
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(ProfileStarredMvp.View::hideProgress);
    return false;
  }
  Observable<Pageable<Repo>> observable;
  if (starredCount == -1) {
    observable=Observable.zip(RestProvider.getUserService(isEnterprise()).getStarred(parameter,page),RestProvider.getUserService(isEnterprise()).getStarredCount(parameter),(repoPageable,count) -> {
      if (count != null) {
        starredCount=count.getLast();
      }
      return repoPageable;
    }
);
  }
 else {
    observable=RestProvider.getUserService(isEnterprise()).getStarred(parameter,page);
  }
  makeRestCall(observable,repoModelPageable -> {
    lastPage=repoModelPageable.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(Repo.saveStarred(repoModelPageable.getItems(),parameter));
    }
    sendToView(view -> {
      view.onUpdateCount(starredCount);
      view.onNotifyAdapter(repoModelPageable.getItems(),page);
    }
);
  }
);
  return true;
}
