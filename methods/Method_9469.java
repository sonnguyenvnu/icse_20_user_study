private void closeCaptionEnter(boolean apply){
  if (currentIndex < 0 || currentIndex >= imagesArrLocals.size()) {
    return;
  }
  Object object=imagesArrLocals.get(currentIndex);
  if (apply) {
    CharSequence caption=captionEditText.getFieldCharSequence();
    CharSequence[] result=new CharSequence[]{caption};
    ArrayList<TLRPC.MessageEntity> entities=DataQuery.getInstance(currentAccount).getEntities(result);
    if (object instanceof MediaController.PhotoEntry) {
      MediaController.PhotoEntry photoEntry=(MediaController.PhotoEntry)object;
      photoEntry.caption=result[0];
      photoEntry.entities=entities;
    }
 else     if (object instanceof MediaController.SearchImage) {
      MediaController.SearchImage photoEntry=(MediaController.SearchImage)object;
      photoEntry.caption=result[0];
      photoEntry.entities=entities;
    }
    if (captionEditText.getFieldCharSequence().length() != 0 && !placeProvider.isPhotoChecked(currentIndex)) {
      setPhotoChecked();
    }
    setCurrentCaption(null,result[0],false);
  }
  captionEditText.setTag(null);
  if (lastTitle != null) {
    actionBar.setTitle(lastTitle);
    lastTitle=null;
  }
  if (isCurrentVideo) {
    actionBar.setSubtitle(muteVideo ? null : currentSubtitle);
  }
  updateCaptionTextForCurrentPhoto(object);
  if (captionEditText.isPopupShowing()) {
    captionEditText.hidePopup();
  }
  captionEditText.closeKeyboard();
  if (Build.VERSION.SDK_INT >= 19)   captionEditText.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS);
}
