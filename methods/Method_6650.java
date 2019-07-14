public void startMediaObserver(){
  ApplicationLoader.applicationHandler.removeCallbacks(stopMediaObserverRunnable);
  startObserverToken++;
  try {
    if (internalObserver == null) {
      ApplicationLoader.applicationContext.getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,false,externalObserver=new ExternalObserver());
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  try {
    if (externalObserver == null) {
      ApplicationLoader.applicationContext.getContentResolver().registerContentObserver(MediaStore.Images.Media.INTERNAL_CONTENT_URI,false,internalObserver=new InternalObserver());
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
