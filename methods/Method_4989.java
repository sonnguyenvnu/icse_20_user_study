@Override public void putDownloadState(DownloadState downloadState){
  getDownloadStateTable().replace(downloadState);
}
