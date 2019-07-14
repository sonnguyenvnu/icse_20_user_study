private void clearSelectedPhotos(){
  boolean changed=false;
  if (!selectedPhotos.isEmpty()) {
    for (    HashMap.Entry<Object,Object> entry : selectedPhotos.entrySet()) {
      MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)entry.getValue();
      photoEntry.reset();
    }
    selectedPhotos.clear();
    selectedPhotosOrder.clear();
    updatePhotosButton();
    changed=true;
  }
  if (!cameraPhotos.isEmpty()) {
    for (int a=0, size=cameraPhotos.size(); a < size; a++) {
      MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)cameraPhotos.get(a);
      new File(photoEntry.path).delete();
      if (photoEntry.imagePath != null) {
        new File(photoEntry.imagePath).delete();
      }
      if (photoEntry.thumbPath != null) {
        new File(photoEntry.thumbPath).delete();
      }
    }
    cameraPhotos.clear();
    changed=true;
  }
  if (changed) {
    photoAttachAdapter.notifyDataSetChanged();
    cameraAttachAdapter.notifyDataSetChanged();
  }
}
