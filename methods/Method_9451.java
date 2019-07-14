private void updateCheckedPhotoIndices(){
  if (!allowIndices) {
    return;
  }
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View view=listView.getChildAt(a);
    if (view instanceof PhotoPickerPhotoCell) {
      PhotoPickerPhotoCell cell=(PhotoPickerPhotoCell)view;
      Integer index=(Integer)cell.getTag();
      if (selectedAlbum != null) {
        MediaController.PhotoEntry photoEntry=selectedAlbum.photos.get(index);
        cell.setNum(allowIndices ? selectedPhotosOrder.indexOf(photoEntry.imageId) : -1);
      }
 else {
        MediaController.SearchImage photoEntry;
        if (searchResult.isEmpty() && lastSearchString == null) {
          photoEntry=recentImages.get(index);
        }
 else {
          photoEntry=searchResult.get(index);
        }
        cell.setNum(allowIndices ? selectedPhotosOrder.indexOf(photoEntry.id) : -1);
      }
    }
  }
}
