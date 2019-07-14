private void updateCheckedPhotoIndices(){
  if (!(baseFragment instanceof ChatActivity)) {
    return;
  }
  int count=attachPhotoRecyclerView.getChildCount();
  for (int a=0; a < count; a++) {
    View view=attachPhotoRecyclerView.getChildAt(a);
    if (view instanceof PhotoAttachPhotoCell) {
      PhotoAttachPhotoCell cell=(PhotoAttachPhotoCell)view;
      MediaController.PhotoEntry photoEntry=getPhotoEntryAtPosition((Integer)cell.getTag());
      if (photoEntry != null) {
        cell.setNum(selectedPhotosOrder.indexOf(photoEntry.imageId));
      }
    }
  }
  count=cameraPhotoRecyclerView.getChildCount();
  for (int a=0; a < count; a++) {
    View view=cameraPhotoRecyclerView.getChildAt(a);
    if (view instanceof PhotoAttachPhotoCell) {
      PhotoAttachPhotoCell cell=(PhotoAttachPhotoCell)view;
      MediaController.PhotoEntry photoEntry=getPhotoEntryAtPosition((Integer)cell.getTag());
      if (photoEntry != null) {
        cell.setNum(selectedPhotosOrder.indexOf(photoEntry.imageId));
      }
    }
  }
}
