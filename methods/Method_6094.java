public static int[] calcDrawableColor(Drawable drawable){
  int bitmapColor=0xff000000;
  int[] result=new int[4];
  try {
    if (drawable instanceof BitmapDrawable) {
      Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
      if (bitmap != null) {
        Bitmap b=Bitmaps.createScaledBitmap(bitmap,1,1,true);
        if (b != null) {
          bitmapColor=b.getPixel(0,0);
          if (bitmap != b) {
            b.recycle();
          }
        }
      }
    }
 else     if (drawable instanceof ColorDrawable) {
      bitmapColor=((ColorDrawable)drawable).getColor();
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  double[] hsv=rgbToHsv((bitmapColor >> 16) & 0xff,(bitmapColor >> 8) & 0xff,bitmapColor & 0xff);
  hsv[1]=Math.min(1.0,hsv[1] + 0.05 + 0.1 * (1.0 - hsv[1]));
  double v=Math.max(0,hsv[2] * 0.65);
  int[] rgb=hsvToRgb(hsv[0],hsv[1],v);
  result[0]=Color.argb(0x66,rgb[0],rgb[1],rgb[2]);
  result[1]=Color.argb(0x88,rgb[0],rgb[1],rgb[2]);
  double v2=Math.max(0,hsv[2] * 0.72);
  rgb=hsvToRgb(hsv[0],hsv[1],v2);
  result[2]=Color.argb(0x66,rgb[0],rgb[1],rgb[2]);
  result[3]=Color.argb(0x88,rgb[0],rgb[1],rgb[2]);
  return result;
}
