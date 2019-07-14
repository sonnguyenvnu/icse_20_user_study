private void setPhotoChecked(){
  if (placeProvider != null) {
    if (placeProvider.getSelectedPhotos() != null && maxSelectedPhotos > 0 && placeProvider.getSelectedPhotos().size() > maxSelectedPhotos && !placeProvider.isPhotoChecked(currentIndex)) {
      return;
    }
    int num=placeProvider.setPhotoChecked(currentIndex,getCurrentVideoEditedInfo());
    boolean checked=placeProvider.isPhotoChecked(currentIndex);
    checkImageView.setChecked(checked,true);
    if (num >= 0) {
      if (placeProvider.allowGroupPhotos()) {
        num++;
      }
      if (checked) {
        selectedPhotosAdapter.notifyItemInserted(num);
        selectedPhotosListView.smoothScrollToPosition(num);
      }
 else {
        selectedPhotosAdapter.notifyItemRemoved(num);
      }
    }
    updateSelectedCount();
  }
}
