@Override public void onError(@NonNull Throwable throwable){
  int code=RestProvider.getErrorCode(throwable);
  if (code == 404) {
    sendToView(BaseMvp.FAView::onOpenUrlInBrowser);
  }
 else {
    onWorkOffline();
  }
  super.onError(throwable);
}
