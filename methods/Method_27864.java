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
    sendToView(TeamMembersMvp.View::hideProgress);
    return false;
  }
  makeRestCall(RestProvider.getOrgService(isEnterprise()).getTeamMembers(parameter,page),response -> {
    lastPage=response.getLast();
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
);
  return true;
}
