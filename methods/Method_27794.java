@Override public void onReload(){
  manageDisposable(PinnedIssues.getMyPinnedIssues().subscribe(repos -> sendToView(view -> view.onNotifyAdapter(repos)),throwable -> sendToView(view -> view.onNotifyAdapter(null))));
}
