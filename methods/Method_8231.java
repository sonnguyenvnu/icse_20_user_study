private void openAttachMenu(){
  if (getParentActivity() == null) {
    return;
  }
  createChatAttachView();
  chatAttachAlert.loadGalleryPhotos();
  if (Build.VERSION.SDK_INT == 21 || Build.VERSION.SDK_INT == 22) {
    chatActivityEnterView.closeKeyboard();
  }
  chatAttachAlert.init();
  showDialog(chatAttachAlert);
}
