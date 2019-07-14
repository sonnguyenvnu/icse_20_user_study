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
    sendToView(OrgReposMvp.View::hideProgress);
    return false;
  }
  filterOptions.setOrg(true);
  makeRestCall(RestProvider.getOrgService(isEnterprise()).getOrgRepos(parameter,filterOptions.getQueryMap(),page),repoModelPageable -> {
    lastPage=repoModelPageable.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(Repo.saveMyRepos(repoModelPageable.getItems(),parameter));
    }
    sendToView(view -> view.onNotifyAdapter(repoModelPageable.getItems(),page));
  }
);
  return true;
}
