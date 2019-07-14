public void init(){
  MediaController.AlbumEntry albumEntry;
  if (baseFragment instanceof ChatActivity) {
    albumEntry=MediaController.allMediaAlbumEntry;
  }
 else {
    albumEntry=MediaController.allPhotosAlbumEntry;
  }
  if (albumEntry != null) {
    for (int a=0; a < Math.min(100,albumEntry.photos.size()); a++) {
      MediaController.PhotoEntry photoEntry=albumEntry.photos.get(a);
      photoEntry.reset();
    }
  }
  if (currentHintAnimation != null) {
    currentHintAnimation.cancel();
    currentHintAnimation=null;
  }
  hintTextView.setAlpha(0.0f);
  hintTextView.setVisibility(View.INVISIBLE);
  attachPhotoLayoutManager.scrollToPositionWithOffset(0,1000000);
  cameraPhotoLayoutManager.scrollToPositionWithOffset(0,1000000);
  clearSelectedPhotos();
  layoutManager.scrollToPositionWithOffset(0,1000000);
  updatePhotosButton();
}
