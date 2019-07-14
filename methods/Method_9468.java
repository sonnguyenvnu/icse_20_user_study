private void openCaptionEnter(){
  if (imageMoveAnimation != null || changeModeAnimation != null || currentEditMode != 0 || sendPhotoType == SELECT_TYPE_AVATAR || sendPhotoType == SELECT_TYPE_WALLPAPER) {
    return;
  }
  selectedPhotosListView.setVisibility(View.GONE);
  selectedPhotosListView.setEnabled(false);
  selectedPhotosListView.setAlpha(0.0f);
  selectedPhotosListView.setTranslationY(-AndroidUtilities.dp(10));
  photosCounterView.setRotationX(0.0f);
  isPhotosListViewVisible=false;
  captionEditText.setTag(1);
  captionEditText.openKeyboard();
  captionEditText.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
  lastTitle=actionBar.getTitle();
  if (isCurrentVideo) {
    actionBar.setTitle(muteVideo ? LocaleController.getString("GifCaption",R.string.GifCaption) : LocaleController.getString("VideoCaption",R.string.VideoCaption));
    actionBar.setSubtitle(null);
  }
 else {
    actionBar.setTitle(LocaleController.getString("PhotoCaption",R.string.PhotoCaption));
  }
}
