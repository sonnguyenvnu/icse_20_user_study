@Override public void didReceivedNotification(int id,int account,Object... args){
  if (id == NotificationCenter.albumsDidLoad) {
    if (photoAttachAdapter != null) {
      loading=false;
      progressView.showTextView();
      photoAttachAdapter.notifyDataSetChanged();
      cameraAttachAdapter.notifyDataSetChanged();
      if (!selectedPhotosOrder.isEmpty()) {
        MediaController.AlbumEntry albumEntry;
        if (baseFragment instanceof ChatActivity) {
          albumEntry=MediaController.allMediaAlbumEntry;
        }
 else {
          albumEntry=MediaController.allPhotosAlbumEntry;
        }
        if (albumEntry != null) {
          for (int a=0, N=selectedPhotosOrder.size(); a < N; a++) {
            int imageId=(Integer)selectedPhotosOrder.get(a);
            MediaController.PhotoEntry entry=albumEntry.photosByIds.get(imageId);
            if (entry != null) {
              selectedPhotos.put(imageId,entry);
            }
          }
        }
      }
    }
  }
 else   if (id == NotificationCenter.reloadInlineHints) {
    if (adapter != null) {
      adapter.notifyDataSetChanged();
    }
  }
 else   if (id == NotificationCenter.cameraInitied) {
    checkCamera(false);
  }
}
