@Override public void update(long read,final long count,boolean done){
  Flowable.just(read).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
    fileInfo.setLoadBytes(aLong.intValue());
    fileInfo.setTotalBytes((int)count);
    if (fileInfo.getStatus() == DownloadStatus.STOP) {
      fileDAO.update(fileInfo);
      listener.onStop(fileInfo);
      return;
    }
    if (fileInfo.getStatus() == DownloadStatus.CANCEL) {
      fileDAO.update(fileInfo);
      listener.onCancel(fileInfo);
      return;
    }
    if (fileInfo == null) {
      CommonLogger.e("??????");
    }
    fileInfo.setStatus(DownloadStatus.DOWNLOADING);
    fileDAO.update(fileInfo);
    listener.onUpdate(fileInfo);
  }
);
}
