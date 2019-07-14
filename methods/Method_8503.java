public void updatePhotosButton(){
  int count=selectedPhotos.size();
  if (count == 0) {
    sendPhotosButton.imageView.setBackgroundDrawable(Theme.chat_attachButtonDrawables[7]);
    sendPhotosButton.textView.setText("");
    sendPhotosButton.textView.setContentDescription(LocaleController.getString("Close",R.string.Close));
    if (baseFragment instanceof ChatActivity) {
      sendDocumentsButton.textView.setText(LocaleController.getString("ChatDocument",R.string.ChatDocument));
    }
  }
 else {
    sendPhotosButton.imageView.setBackgroundDrawable(Theme.chat_attachButtonDrawables[8]);
    sendPhotosButton.textView.setContentDescription(null);
    if (baseFragment instanceof ChatActivity) {
      sendPhotosButton.textView.setText(LocaleController.formatString("SendItems",R.string.SendItems,String.format("(%d)",count)));
      if (editingMessageObject == null || !editingMessageObject.hasValidGroupId()) {
        sendDocumentsButton.textView.setText(count == 1 ? LocaleController.getString("SendAsFile",R.string.SendAsFile) : LocaleController.getString("SendAsFiles",R.string.SendAsFiles));
      }
    }
 else {
      sendPhotosButton.textView.setText(LocaleController.formatString("UploadItems",R.string.UploadItems,String.format("(%d)",count)));
    }
  }
  if (Build.VERSION.SDK_INT >= 23 && getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    progressView.setText(LocaleController.getString("PermissionStorage",R.string.PermissionStorage));
    progressView.setTextSize(16);
  }
 else {
    progressView.setText(LocaleController.getString("NoPhotos",R.string.NoPhotos));
    progressView.setTextSize(20);
  }
}
