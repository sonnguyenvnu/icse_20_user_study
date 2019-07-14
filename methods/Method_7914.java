public void setStickersSet(TLRPC.StickerSetCovered set,boolean divider){
  needDivider=divider;
  stickersSet=set;
  setWillNotDraw(!needDivider);
  textView.setText(stickersSet.set.title);
  valueTextView.setText(LocaleController.formatPluralString("Stickers",set.set.count));
  TLRPC.PhotoSize thumb=set.cover != null ? FileLoader.getClosestPhotoSizeWithSize(set.cover.thumbs,90) : null;
  if (thumb != null && thumb.location != null) {
    imageView.setImage(ImageLocation.getForDocument(thumb,set.cover),null,"webp",null,set);
  }
 else   if (!set.covers.isEmpty()) {
    TLRPC.Document document=set.covers.get(0);
    thumb=FileLoader.getClosestPhotoSizeWithSize(document.thumbs,90);
    imageView.setImage(ImageLocation.getForDocument(thumb,document),null,"webp",null,set);
  }
 else {
    imageView.setImage(null,null,"webp",null,set);
  }
}
