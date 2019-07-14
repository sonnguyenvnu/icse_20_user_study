@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(RepoReleasesMvp.View::hideProgress);
    return false;
  }
  if (repoId == null || login == null)   return false;
  makeRestCall(RestProvider.getRepoService(isEnterprise()).getReleases(login,repoId,page),response -> {
    if (response.getItems() == null || response.getItems().isEmpty()) {
      makeRestCall(RestProvider.getRepoService(isEnterprise()).getTagReleases(login,repoId,page),this::onResponse);
      return;
    }
    onResponse(response);
  }
);
  return true;
}
