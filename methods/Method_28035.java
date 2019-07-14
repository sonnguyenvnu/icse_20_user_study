@Override public void onError(@NonNull Throwable throwable){
  throwable.printStackTrace();
  int code=RestProvider.getErrorCode(throwable);
  if (code == 404) {
    if (!isRepo) {
      sendToView(view -> view.onShowError(R.string.no_file_found));
    }
    sendToView(BaseMvp.FAView::hideProgress);
  }
 else {
    if (code == 406) {
      sendToView(view -> {
        view.hideProgress();
        view.openUrl(url);
      }
);
      return;
    }
    onWorkOffline();
    super.onError(throwable);
  }
}
