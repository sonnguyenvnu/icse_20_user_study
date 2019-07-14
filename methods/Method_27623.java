@Override public boolean onCallApi(int page,@Nullable String parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || parameter == null || lastPage == 0) {
    sendToView(GistCommentsMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  makeRestCall(RestProvider.getGistService(isEnterprise()).getGistComments(parameter,page),listResponse -> {
    lastPage=listResponse.getLast();
    if (getCurrentPage() == 1) {
      manageDisposable(Comment.saveForGist(listResponse.getItems(),parameter));
    }
    sendToView(view -> view.onNotifyAdapter(listResponse.getItems(),page));
  }
);
  return true;
}
