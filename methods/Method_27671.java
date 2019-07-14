@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(GistsMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  makeRestCall(RestProvider.getGistService(isEnterprise()).getPublicGists(RestProvider.PAGE_SIZE,page),listResponse -> {
    lastPage=listResponse.getLast();
    sendToView(view -> view.onNotifyAdapter(listResponse.getItems(),page));
  }
);
  return true;
}
