private int addToSelectedPhotos(MediaController.PhotoEntry object,int index){
  Object key=object.imageId;
  if (selectedPhotos.containsKey(key)) {
    selectedPhotos.remove(key);
    int position=selectedPhotosOrder.indexOf(key);
    if (position >= 0) {
      selectedPhotosOrder.remove(position);
    }
    updatePhotosCounter();
    updateCheckedPhotoIndices();
    if (index >= 0) {
      object.reset();
      photoViewerProvider.updatePhotoAtIndex(index);
    }
    return position;
  }
 else {
    selectedPhotos.put(key,object);
    selectedPhotosOrder.add(key);
    updatePhotosCounter();
    return -1;
  }
}
