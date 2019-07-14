private void onResponse(Pageable<Release> response){
  lastPage=response.getLast();
  if (getCurrentPage() == 1) {
    manageDisposable(Release.save(response.getItems(),repoId,login));
  }
  sendToView(view -> view.onNotifyAdapter(response.getItems(),getCurrentPage()));
}
