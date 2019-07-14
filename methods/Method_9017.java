public void setImages(){
  int tabSize=AndroidUtilities.dp(52);
  int start=getScrollX() / tabSize;
  int end=Math.min(tabsContainer.getChildCount(),start + (int)Math.ceil(getMeasuredWidth() / (float)tabSize) + 1);
  for (int a=start; a < end; a++) {
    View child=tabsContainer.getChildAt(a);
    Object object=child.getTag();
    Object parentObject=child.getTag(R.id.parent_tag);
    TLRPC.Document sticker=(TLRPC.Document)child.getTag(R.id.object_tag);
    ImageLocation imageLocation;
    if (object instanceof TLRPC.Document) {
      TLRPC.PhotoSize thumb=FileLoader.getClosestPhotoSizeWithSize(sticker.thumbs,90);
      imageLocation=ImageLocation.getForDocument(thumb,sticker);
    }
 else     if (object instanceof TLRPC.PhotoSize) {
      TLRPC.PhotoSize thumb=(TLRPC.PhotoSize)object;
      imageLocation=ImageLocation.getForSticker(thumb,sticker);
    }
 else {
      continue;
    }
    BackupImageView imageView=(BackupImageView)((FrameLayout)child).getChildAt(0);
    imageView.setImage(imageLocation,null,"webp",null,parentObject);
  }
}
