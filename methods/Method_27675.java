@Override public boolean onCallApi(int page,@Nullable String parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  setCurrentPage(page);
  if (page > lastPage || lastPage == 0) {
    sendToView(StarredGistsMvp.View::hideProgress);
    return false;
  }
  makeRestCall(RestProvider.getGistService(isEnterprise()).getStarredGists(page),listResponse -> {
    lastPage=listResponse.getLast();
    sendToView(view -> view.onNotifyAdapter(listResponse.getItems(),page));
  }
);
  return true;
}
