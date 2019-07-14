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
    sendToView(ProfileFollowingMvp.View::hideProgress);
    return false;
  }
  makeRestCall(RestProvider.getUserService(isEnterprise()).getFollowing(parameter,page),response -> {
    lastPage=response.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(User.saveUserFollowingList(response.getItems(),parameter));
    }
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
);
  return true;
}
