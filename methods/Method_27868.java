@Override public boolean onCallApi(int page,@Nullable Long parameter){
  if (parameter == null) {
    throw new NullPointerException("Username is null");
  }
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(TeamReposMvp.View::hideProgress);
    return false;
  }
  makeRestCall(RestProvider.getOrgService(isEnterprise()).getTeamRepos(parameter,page),repoModelPageable -> {
    lastPage=repoModelPageable.getLast();
    sendToView(view -> view.onNotifyAdapter(repoModelPageable.getItems(),page));
  }
);
  return true;
}
