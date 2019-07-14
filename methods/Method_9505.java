private void setCropBitmap(){
  if (sendPhotoType != SELECT_TYPE_AVATAR) {
    return;
  }
  Bitmap bitmap=centerImage.getBitmap();
  int orientation=centerImage.getOrientation();
  if (bitmap == null) {
    bitmap=animatingImageView.getBitmap();
    orientation=animatingImageView.getOrientation();
  }
  if (bitmap != null) {
    photoCropView.setBitmap(bitmap,orientation,false,false);
    if (currentEditMode == 0) {
      setCropTranslations(false);
    }
  }
}
