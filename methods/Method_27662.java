@Override public void onWorkOffline(@NonNull String gistId){
  if (gist == null) {
    manageDisposable(RxHelper.getObservable(Gist.getGist(gistId)).subscribe(gistsModel -> {
      this.gist=gistsModel;
      sendToView(GistMvp.View::onSetupDetails);
    }
));
  }
}
