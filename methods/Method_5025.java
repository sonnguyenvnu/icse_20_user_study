private void notifyDownloadStateChange(DownloadState downloadState){
  onDownloadStateChanged(downloadState);
  if (foregroundNotificationUpdater != null) {
    if (downloadState.state == DownloadState.STATE_DOWNLOADING || downloadState.state == DownloadState.STATE_REMOVING || downloadState.state == DownloadState.STATE_RESTARTING) {
      foregroundNotificationUpdater.startPeriodicUpdates();
    }
 else {
      foregroundNotificationUpdater.update();
    }
  }
}
