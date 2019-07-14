@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.FileUploadProgressChanged) {
    String fileName=(String)args[0];
    if (account == currentAccount && path != null && path.equals(fileName)) {
      Float progress=(Float)args[1];
      Boolean enc=(Boolean)args[2];
      currentProgress=(int)(progress * 100);
      builder.setProgress(100,currentProgress,currentProgress == 0);
      try {
        NotificationManagerCompat.from(ApplicationLoader.applicationContext).notify(4,builder.build());
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
    }
  }
 else   if (id == NotificationCenter.stopEncodingService) {
    String filepath=(String)args[0];
    account=(Integer)args[1];
    if (account == currentAccount && (filepath == null || filepath.equals(path))) {
      stopSelf();
    }
  }
}
