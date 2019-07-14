private void onRevealAnimationEnd(boolean open){
  NotificationCenter.getInstance(currentAccount).setAnimationInProgress(false);
  revealAnimationInProgress=false;
  MediaController.AlbumEntry albumEntry;
  if (baseFragment instanceof ChatActivity) {
    albumEntry=MediaController.allMediaAlbumEntry;
  }
 else {
    albumEntry=MediaController.allPhotosAlbumEntry;
  }
  if (open && Build.VERSION.SDK_INT <= 19 && albumEntry == null) {
    MediaController.loadGalleryPhotosAlbums(0);
  }
  if (open) {
    checkCamera(true);
    showHint();
    AndroidUtilities.makeAccessibilityAnnouncement(LocaleController.getString("AccDescrAttachButton",R.string.AccDescrAttachButton));
  }
}
