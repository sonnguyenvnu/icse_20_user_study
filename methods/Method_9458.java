private void sendSelectedPhotos(){
  if (selectedPhotos.isEmpty() || delegate == null || sendPressed) {
    return;
  }
  sendPressed=true;
  delegate.actionButtonPressed(false);
  if (selectPhotoType != 2) {
    finishFragment();
  }
}
