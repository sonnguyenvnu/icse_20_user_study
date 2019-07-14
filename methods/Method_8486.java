private MediaController.PhotoEntry getPhotoEntryAtPosition(int position){
  if (position < 0) {
    return null;
  }
  int cameraCount=cameraPhotos.size();
  if (position < cameraCount) {
    return (MediaController.PhotoEntry)cameraPhotos.get(position);
  }
  position-=cameraCount;
  MediaController.AlbumEntry albumEntry;
  if (baseFragment instanceof ChatActivity) {
    albumEntry=MediaController.allMediaAlbumEntry;
  }
 else {
    albumEntry=MediaController.allPhotosAlbumEntry;
  }
  if (position < albumEntry.photos.size()) {
    return albumEntry.photos.get(position);
  }
  return null;
}
