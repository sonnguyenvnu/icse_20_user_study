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
    sendToView(ProfileGistsMvp.View::hideProgress);
    return false;
  }
  makeRestCall(RestProvider.getGistService(isEnterprise()).getUserGists(parameter,page),listResponse -> {
    lastPage=listResponse.getLast();
    sendToView(view -> view.onNotifyAdapter(listResponse.getItems(),page));
    manageDisposable(Gist.save(Stream.of(listResponse.getItems()).toList(),parameter));
  }
);
  return true;
}
