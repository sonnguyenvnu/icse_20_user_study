private static void broadcastNewPhotos(final int guid,final ArrayList<AlbumEntry> mediaAlbumsSorted,final ArrayList<AlbumEntry> photoAlbumsSorted,final Integer cameraAlbumIdFinal,final AlbumEntry allMediaAlbumFinal,final AlbumEntry allPhotosAlbumFinal,final AlbumEntry allVideosAlbumFinal,int delay){
  if (broadcastPhotosRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(broadcastPhotosRunnable);
  }
  AndroidUtilities.runOnUIThread(broadcastPhotosRunnable=() -> {
    if (PhotoViewer.getInstance().isVisible()) {
      broadcastNewPhotos(guid,mediaAlbumsSorted,photoAlbumsSorted,cameraAlbumIdFinal,allMediaAlbumFinal,allPhotosAlbumFinal,allVideosAlbumFinal,1000);
      return;
    }
    broadcastPhotosRunnable=null;
    allPhotosAlbumEntry=allPhotosAlbumFinal;
    allMediaAlbumEntry=allMediaAlbumFinal;
    allVideosAlbumEntry=allVideosAlbumFinal;
    for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
      NotificationCenter.getInstance(a).postNotificationName(NotificationCenter.albumsDidLoad,guid,mediaAlbumsSorted,photoAlbumsSorted,cameraAlbumIdFinal);
    }
  }
,delay);
}
