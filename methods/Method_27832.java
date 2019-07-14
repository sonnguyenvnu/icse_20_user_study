@Override public void onWorkOffline(@NonNull String login){
  if (gistsModels.isEmpty()) {
    manageDisposable(RxHelper.getObservable(Gist.getMyGists(login).toObservable()).subscribe(gistsModels1 -> sendToView(view -> view.onNotifyAdapter(gistsModels1,1))));
  }
 else {
    sendToView(ProfileGistsMvp.View::hideProgress);
  }
}
