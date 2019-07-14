public void setDocument(MessageObject messageObject,boolean divider){
  needDivider=divider;
  message=messageObject;
  loaded=false;
  loading=false;
  TLRPC.Document document=messageObject.getDocument();
  if (messageObject != null && document != null) {
    int idx;
    String name=null;
    if (messageObject.isMusic()) {
      for (int a=0; a < document.attributes.size(); a++) {
        TLRPC.DocumentAttribute attribute=document.attributes.get(a);
        if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
          if (attribute.performer != null && attribute.performer.length() != 0 || attribute.title != null && attribute.title.length() != 0) {
            name=messageObject.getMusicAuthor() + " - " + messageObject.getMusicTitle();
          }
        }
      }
    }
    String fileName=FileLoader.getDocumentFileName(document);
    if (name == null) {
      name=fileName;
    }
    nameTextView.setText(name);
    placeholderImageView.setVisibility(VISIBLE);
    extTextView.setVisibility(VISIBLE);
    placeholderImageView.setImageResource(AndroidUtilities.getThumbForNameOrMime(fileName,document.mime_type,false));
    extTextView.setText((idx=fileName.lastIndexOf('.')) == -1 ? "" : fileName.substring(idx + 1).toLowerCase());
    TLRPC.PhotoSize bigthumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,320);
    TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,40);
    if (thumb == bigthumb) {
      bigthumb=null;
    }
    if (thumb instanceof TLRPC.TL_photoSizeEmpty || thumb == null) {
      thumbImageView.setVisibility(INVISIBLE);
      thumbImageView.setImageBitmap(null);
      extTextView.setAlpha(1.0f);
      placeholderImageView.setAlpha(1.0f);
    }
 else {
      thumbImageView.getImageReceiver().setNeedsQualityThumb(bigthumb == null);
      thumbImageView.getImageReceiver().setShouldGenerateQualityThumb(bigthumb == null);
      thumbImageView.setVisibility(VISIBLE);
      thumbImageView.setImage(ImageLocation.getForDocument(bigthumb,document),"40_40",ImageLocation.getForDocument(thumb,document),"40_40_b",null,0,1,messageObject);
    }
    long date=(long)messageObject.messageOwner.date * 1000;
    dateTextView.setText(String.format("%s, %s",AndroidUtilities.formatFileSize(document.size),LocaleController.formatString("formatDateAtTime",R.string.formatDateAtTime,LocaleController.getInstance().formatterYear.format(new Date(date)),LocaleController.getInstance().formatterDay.format(new Date(date)))));
  }
 else {
    nameTextView.setText("");
    extTextView.setText("");
    dateTextView.setText("");
    placeholderImageView.setVisibility(VISIBLE);
    extTextView.setVisibility(VISIBLE);
    extTextView.setAlpha(1.0f);
    placeholderImageView.setAlpha(1.0f);
    thumbImageView.setVisibility(INVISIBLE);
    thumbImageView.setImageBitmap(null);
  }
  setWillNotDraw(!needDivider);
  progressView.setProgress(0,false);
  updateFileExistIcon();
}
