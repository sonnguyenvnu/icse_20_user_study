private int addToSelectedPhotos(Object object,int index){
  Object key=null;
  if (object instanceof MediaController.PhotoEntry) {
    key=((MediaController.PhotoEntry)object).imageId;
  }
 else   if (object instanceof MediaController.SearchImage) {
    key=((MediaController.SearchImage)object).id;
  }
  if (key == null) {
    return -1;
  }
  if (selectedPhotos.containsKey(key)) {
    selectedPhotos.remove(key);
    int position=selectedPhotosOrder.indexOf(key);
    if (position >= 0) {
      selectedPhotosOrder.remove(position);
    }
    if (allowIndices) {
      updateCheckedPhotoIndices();
    }
    if (index >= 0) {
      if (object instanceof MediaController.PhotoEntry) {
        ((MediaController.PhotoEntry)object).reset();
      }
 else       if (object instanceof MediaController.SearchImage) {
        ((MediaController.SearchImage)object).reset();
      }
      provider.updatePhotoAtIndex(index);
    }
    return position;
  }
 else {
    selectedPhotos.put(key,object);
    selectedPhotosOrder.add(key);
    return -1;
  }
}
