@Override public void onReload(){
  manageDisposable(PinnedPullRequests.getMyPinnedPullRequests().subscribe(repos -> sendToView(view -> view.onNotifyAdapter(repos)),throwable -> sendToView(view -> view.onNotifyAdapter(null))));
}
