public void loadWebRecent(final int type){
  storageQueue.postRunnable(() -> {
    try {
      final ArrayList<MediaController.SearchImage> arrayList=new ArrayList<>();
      AndroidUtilities.runOnUIThread(() -> NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.recentImagesDidLoad,type,arrayList));
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
  }
);
}
