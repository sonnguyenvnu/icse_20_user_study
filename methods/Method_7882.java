private void onPhotoClosed(PlaceProviderObject object){
  isPhotoVisible=false;
  disableShowCheck=true;
  currentMedia=null;
  if (currentThumb != null) {
    currentThumb.release();
    currentThumb=null;
  }
  if (currentAnimation != null) {
    currentAnimation.setSecondParentView(null);
    currentAnimation=null;
  }
  for (int a=0; a < 3; a++) {
    if (radialProgressViews[a] != null) {
      radialProgressViews[a].setBackgroundState(-1,false);
    }
  }
  centerImage.setImageBitmap((Bitmap)null);
  leftImage.setImageBitmap((Bitmap)null);
  rightImage.setImageBitmap((Bitmap)null);
  photoContainerView.post(() -> animatingImageView.setImageBitmap(null));
  disableShowCheck=false;
  if (object != null) {
    object.imageReceiver.setVisible(true,true);
  }
  groupedPhotosListView.clear();
}
