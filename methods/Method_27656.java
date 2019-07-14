@SuppressWarnings("unchecked") @Override public void onActivityCreated(@Nullable Intent intent){
  if (intent == null || intent.getExtras() == null) {
    return;
  }
  Bundle bundle=intent.getExtras();
  gistId=bundle.getString(BundleConstant.EXTRA);
  if (gist != null) {
    checkStarring(gist.getGistId());
    sendToView(GistMvp.View::onSetupDetails);
  }
 else   if (gistId != null) {
    callApi();
  }
 else {
    sendToView(GistMvp.View::onSetupDetails);
  }
}
