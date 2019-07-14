public void setMessageObject(MessageObject messageObject){
  currentMessageObject=messageObject;
  TLRPC.Document document=messageObject.getDocument();
  TLRPC.PhotoSize thumb=document != null ? FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90) : null;
  if (thumb instanceof TLRPC.TL_photoSize) {
    radialProgress.setImageOverlay(thumb,document,messageObject);
  }
 else {
    String artworkUrl=messageObject.getArtworkUrl(true);
    if (!TextUtils.isEmpty(artworkUrl)) {
      radialProgress.setImageOverlay(artworkUrl);
    }
 else {
      radialProgress.setImageOverlay(null,null,null);
    }
  }
  requestLayout();
  updateButtonState(false,false);
}
