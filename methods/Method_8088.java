public void setHasLocation(boolean value){
  LocationController.SharingLocationInfo info=LocationController.getInstance(currentAccount).getSharingLocationInfo(dialogId);
  if (info == null) {
    titleTextView.setAlpha(value ? 1.0f : 0.5f);
    accurateTextView.setAlpha(value ? 1.0f : 0.5f);
    imageView.setAlpha(value ? 1.0f : 0.5f);
  }
}
