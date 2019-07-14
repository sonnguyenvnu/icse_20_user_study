@Override public void onError(@NonNull Throwable throwable){
  if (RestProvider.getErrorCode(throwable) == 404) {
    sendToView(BaseMvp.FAView::onOpenUrlInBrowser);
  }
 else {
    onWorkOffline();
  }
  super.onError(throwable);
}
