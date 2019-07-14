public static TLRPC.PhotoSize scaleAndSaveImage(TLRPC.PhotoSize photoSize,Bitmap bitmap,float maxWidth,float maxHeight,int quality,boolean cache,int minWidth,int minHeight){
  if (bitmap == null) {
    return null;
  }
  float photoW=bitmap.getWidth();
  float photoH=bitmap.getHeight();
  if (photoW == 0 || photoH == 0) {
    return null;
  }
  boolean scaleAnyway=false;
  float scaleFactor=Math.max(photoW / maxWidth,photoH / maxHeight);
  if (minWidth != 0 && minHeight != 0 && (photoW < minWidth || photoH < minHeight)) {
    if (photoW < minWidth && photoH > minHeight) {
      scaleFactor=photoW / minWidth;
    }
 else     if (photoW > minWidth && photoH < minHeight) {
      scaleFactor=photoH / minHeight;
    }
 else {
      scaleFactor=Math.max(photoW / minWidth,photoH / minHeight);
    }
    scaleAnyway=true;
  }
  int w=(int)(photoW / scaleFactor);
  int h=(int)(photoH / scaleFactor);
  if (h == 0 || w == 0) {
    return null;
  }
  try {
    return scaleAndSaveImageInternal(photoSize,bitmap,w,h,photoW,photoH,scaleFactor,quality,cache,scaleAnyway);
  }
 catch (  Throwable e) {
    FileLog.e(e);
    ImageLoader.getInstance().clearMemory();
    System.gc();
    try {
      return scaleAndSaveImageInternal(photoSize,bitmap,w,h,photoW,photoH,scaleFactor,quality,cache,scaleAnyway);
    }
 catch (    Throwable e2) {
      FileLog.e(e2);
      return null;
    }
  }
}
