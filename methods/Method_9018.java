@Override protected void onScrollChanged(int l,int t,int oldl,int oldt){
  super.onScrollChanged(l,t,oldl,oldt);
  int tabSize=AndroidUtilities.dp(52);
  int oldStart=oldl / tabSize;
  int newStart=l / tabSize;
  int count=(int)Math.ceil(getMeasuredWidth() / (float)tabSize) + 1;
  int start=Math.max(0,Math.min(oldStart,newStart));
  int end=Math.min(tabsContainer.getChildCount(),Math.max(oldStart,newStart) + count);
  for (int a=start; a < end; a++) {
    View child=tabsContainer.getChildAt(a);
    if (child == null) {
      continue;
    }
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
    if (a < newStart || a >= newStart + count) {
      imageView.setImageDrawable(null);
    }
 else {
      imageView.setImage(imageLocation,null,"webp",null,parentObject);
    }
  }
}
