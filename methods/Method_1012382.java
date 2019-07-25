public void stop(String url){
  if (url == null) {
    return;
  }
  FileInfo info;
  info=newFileInfoMap.get(url);
  if (info != null) {
    info.setStatus(DownloadStatus.STOP);
    unSubscrible(url);
  }
  daoSession.update(info);
}
