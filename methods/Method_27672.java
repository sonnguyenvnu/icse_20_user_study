@Override public void onWorkOffline(){
  if (gistsModels.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Gist.getGists().toObservable()).subscribe(gists -> sendToView(view -> view.onNotifyAdapter(gists,1))));
  }
 else {
    sendToView(GistsMvp.View::hideProgress);
  }
}
