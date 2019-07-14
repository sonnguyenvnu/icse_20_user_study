@Override public void onReload(){
  manageDisposable(PinnedGists.getMyPinnedGists().subscribe(repos -> sendToView(view -> view.onNotifyAdapter(repos)),throwable -> sendToView(view -> view.onNotifyAdapter(null))));
}
