@Override public void onContentChanged(int progress){
  if (loader != null) {
    loader.setProgress(progress);
    if (progress == 100) {
      hideProgress();
      if (!getPresenter().isMarkDown() && !getPresenter().isImage()) {
        webView.scrollToLine(getPresenter().url());
      }
    }
  }
}
