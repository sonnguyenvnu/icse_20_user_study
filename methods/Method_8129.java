public void setStickersSet(TLRPC.TL_messages_stickerSet set,boolean divider){
  needDivider=divider;
  stickersSet=set;
  imageView.setVisibility(VISIBLE);
  if (progressView != null) {
    progressView.setVisibility(INVISIBLE);
  }
  textView.setTranslationY(0);
  textView.setText(stickersSet.set.title);
  if (stickersSet.set.archived) {
    textView.setAlpha(0.5f);
    valueTextView.setAlpha(0.5f);
    imageView.setAlpha(0.5f);
  }
 else {
    textView.setAlpha(1.0f);
    valueTextView.setAlpha(1.0f);
    imageView.setAlpha(1.0f);
  }
  ArrayList<TLRPC.Document> documents=set.documents;
  if (documents != null && !documents.isEmpty()) {
    valueTextView.setText(LocaleController.formatPluralString("Stickers",documents.size()));
    TLRPC.Document document=documents.get(0);
    TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
    imageView.setImage(ImageLocation.getForDocument(thumb,document),null,"webp",null,set);
  }
 else {
    valueTextView.setText(LocaleController.formatPluralString("Stickers",0));
  }
}
