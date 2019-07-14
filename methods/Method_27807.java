@Override public void onReload(){
  manageDisposable(AbstractPinnedRepos.getMyPinnedRepos().subscribe(repos -> sendToView(view -> view.onNotifyAdapter(repos)),throwable -> sendToView(view -> view.onNotifyAdapter(null))));
}
