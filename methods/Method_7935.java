private void buildLayout(){
  CharSequence text;
  if (currentMessageObject != null) {
    if (currentMessageObject.messageOwner != null && currentMessageObject.messageOwner.media != null && currentMessageObject.messageOwner.media.ttl_seconds != 0) {
      if (currentMessageObject.messageOwner.media.photo instanceof TLRPC.TL_photoEmpty) {
        text=LocaleController.getString("AttachPhotoExpired",R.string.AttachPhotoExpired);
      }
 else       if (currentMessageObject.messageOwner.media.document instanceof TLRPC.TL_documentEmpty) {
        text=LocaleController.getString("AttachVideoExpired",R.string.AttachVideoExpired);
      }
 else {
        text=currentMessageObject.messageText;
      }
    }
 else {
      text=currentMessageObject.messageText;
    }
  }
 else {
    text=customText;
  }
  createLayout(text,previousWidth);
  if (currentMessageObject != null && currentMessageObject.type == 11) {
    imageReceiver.setImageCoords((previousWidth - AndroidUtilities.dp(64)) / 2,textHeight + AndroidUtilities.dp(15),AndroidUtilities.dp(64),AndroidUtilities.dp(64));
  }
}
