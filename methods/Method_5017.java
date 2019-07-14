private void notifyListenersDownloadStateChange(Download download){
  logd("Download state is changed",download);
  DownloadState downloadState=download.getDownloadState();
  for (  Listener listener : listeners) {
    listener.onDownloadStateChanged(this,downloadState);
  }
}
