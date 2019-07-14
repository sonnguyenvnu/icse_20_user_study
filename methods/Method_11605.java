private void onDownloaderFail(Request request){
  if (site.getCycleRetryTimes() == 0) {
    sleep(site.getSleepTime());
  }
 else {
    doCycleRetry(request);
  }
}
