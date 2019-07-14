private static String getKeyForPhotoSize(TLRPC.PhotoSize photoSize,Bitmap[] bitmap,boolean blur){
  if (photoSize == null) {
    return null;
  }
  int maxPhotoWidth;
  int photoWidth;
  int photoHeight;
  if (AndroidUtilities.isTablet()) {
    maxPhotoWidth=photoWidth=(int)(AndroidUtilities.getMinTabletSide() * 0.7f);
  }
 else {
    if (photoSize.w >= photoSize.h) {
      maxPhotoWidth=photoWidth=Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) - AndroidUtilities.dp(64);
    }
 else {
      maxPhotoWidth=photoWidth=(int)(Math.min(AndroidUtilities.displaySize.x,AndroidUtilities.displaySize.y) * 0.7f);
    }
  }
  photoHeight=photoWidth + AndroidUtilities.dp(100);
  if (photoWidth > AndroidUtilities.getPhotoSize()) {
    photoWidth=AndroidUtilities.getPhotoSize();
  }
  if (photoHeight > AndroidUtilities.getPhotoSize()) {
    photoHeight=AndroidUtilities.getPhotoSize();
  }
  float scale=(float)photoSize.w / (float)photoWidth;
  int w=(int)(photoSize.w / scale);
  int h=(int)(photoSize.h / scale);
  if (w == 0) {
    w=AndroidUtilities.dp(150);
  }
  if (h == 0) {
    h=AndroidUtilities.dp(150);
  }
  if (h > photoHeight) {
    float scale2=h;
    h=photoHeight;
    scale2/=h;
    w=(int)(w / scale2);
  }
 else   if (h < AndroidUtilities.dp(120)) {
    h=AndroidUtilities.dp(120);
    float hScale=(float)photoSize.h / h;
    if (photoSize.w / hScale < photoWidth) {
      w=(int)(photoSize.w / hScale);
    }
  }
  if (bitmap != null) {
    try {
      BitmapFactory.Options opts=new BitmapFactory.Options();
      opts.inJustDecodeBounds=true;
      File file=FileLoader.getPathToAttach(photoSize);
      FileInputStream is=new FileInputStream(file);
      BitmapFactory.decodeStream(is,null,opts);
      is.close();
      float photoW=opts.outWidth;
      float photoH=opts.outHeight;
      float scaleFactor=Math.max(photoW / w,photoH / h);
      if (scaleFactor < 1) {
        scaleFactor=1;
      }
      opts.inJustDecodeBounds=false;
      opts.inSampleSize=(int)scaleFactor;
      opts.inPreferredConfig=Bitmap.Config.RGB_565;
      if (Build.VERSION.SDK_INT >= 21) {
        is=new FileInputStream(file);
        bitmap[0]=BitmapFactory.decodeStream(is,null,opts);
        is.close();
      }
 else {
      }
    }
 catch (    Throwable ignore) {
    }
  }
  return String.format(Locale.US,blur ? "%d_%d@%d_%d_b" : "%d_%d@%d_%d",photoSize.location.volume_id,photoSize.location.local_id,(int)(w / AndroidUtilities.density),(int)(h / AndroidUtilities.density));
}
