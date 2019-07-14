@Override public boolean onCallApi(int page,@Nullable String parameter){
  if (currentLoggedIn == null) {
    currentLoggedIn=Login.getUser().getLogin();
  }
  if (parameter == null) {
    throw new NullPointerException("Username is null");
  }
  username=parameter;
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(ProfileReposMvp.View::hideProgress);
    return false;
  }
  boolean isProfile=TextUtils.equals(currentLoggedIn,username);
  filterOptions.setIsPersonalProfile(isProfile);
  makeRestCall(isProfile ? RestProvider.getUserService(isEnterprise()).getRepos(filterOptions.getQueryMap(),page) : RestProvider.getUserService(isEnterprise()).getRepos(parameter,filterOptions.getQueryMap(),page),repoModelPageable -> {
    lastPage=repoModelPageable.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(Repo.saveMyRepos(repoModelPageable.getItems(),parameter));
    }
    sendToView(view -> view.onNotifyAdapter(repoModelPageable.getItems(),page));
  }
);
  return true;
}
