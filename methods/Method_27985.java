@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(RepoContributorsMvp.View::hideProgress);
    return false;
  }
  makeRestCall(RestProvider.getRepoService(isEnterprise()).getContributors(login,repoId,page),response -> {
    if (response != null) {
      lastPage=response.getLast();
    }
    sendToView(view -> view.onNotifyAdapter(response != null ? response.getItems() : null,page));
  }
);
  return true;
}
