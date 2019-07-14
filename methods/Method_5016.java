private void onDownloadStateChange(Download download){
  if (released) {
    return;
  }
  boolean idle=download.isIdle();
  if (idle) {
    activeDownloads.remove(download);
  }
  notifyListenersDownloadStateChange(download);
  if (download.isFinished()) {
    downloads.remove(download);
    saveActions();
  }
  if (idle) {
    for (int i=0; i < downloads.size(); i++) {
      maybeStartDownload(downloads.get(i));
    }
    maybeNotifyListenersIdle();
  }
}
