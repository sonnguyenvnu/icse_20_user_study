@Override public boolean onCallApi(int page,@Nullable Object parameter){
  if (page == 1) {
    lastPage=Integer.MAX_VALUE;
    sendToView(view -> view.getLoadMore().reset());
  }
  if (page > lastPage || lastPage == 0) {
    sendToView(LabelsMvp.View::hideProgress);
    return false;
  }
  setCurrentPage(page);
  makeRestCall(RestProvider.getRepoService(isEnterprise()).getLabels(login,repoId,page),response -> {
    lastPage=response.getLast();
    sendToView(view -> view.onNotifyAdapter(response.getItems(),page));
  }
);
  return true;
}
