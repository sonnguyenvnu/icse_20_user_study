@Override protected void onHandleIntent(Intent intent){
  Timber.v("MMS has finished downloading, persisting it to the database");
  String path=intent.getStringExtra(EXTRA_FILE_PATH);
  Timber.v(path);
  FileInputStream reader=null;
  try {
    File mDownloadFile=new File(path);
    final int nBytes=(int)mDownloadFile.length();
    reader=new FileInputStream(mDownloadFile);
    final byte[] response=new byte[nBytes];
    reader.read(response,0,nBytes);
    CommonNotificationTask task=getNotificationTask(this,intent,response);
    executeNotificationTask(task);
    DownloadRequest.persist(this,response,new MmsConfig.Overridden(new MmsConfig(this),null),intent.getStringExtra(EXTRA_LOCATION_URL),Utils.getDefaultSubscriptionId(),null);
    Timber.v("response saved successfully");
    Timber.v("response length: " + response.length);
    mDownloadFile.delete();
  }
 catch (  FileNotFoundException e) {
    Timber.e(e,"MMS received, file not found exception");
  }
catch (  IOException e) {
    Timber.e(e,"MMS received, io exception");
  }
 finally {
    if (reader != null) {
      try {
        reader.close();
      }
 catch (      IOException e) {
        Timber.e(e,"MMS received, io exception");
      }
    }
    handleHttpError(this,intent);
    DownloadManager.finishDownload(intent.getStringExtra(EXTRA_LOCATION_URL));
  }
}
