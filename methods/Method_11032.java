protected void createBitmaps(){
  int width=getWidth();
  int height=getHeight();
  bar=Bitmap.createBitmap(width - barOffsetX * 2,barHeight,Bitmap.Config.ARGB_8888);
  barCanvas=new Canvas(bar);
  if (bitmap == null || bitmap.getWidth() != width || bitmap.getHeight() != height) {
    if (bitmap != null) {
      bitmap.recycle();
    }
    bitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
    bitmapCanvas=new Canvas(bitmap);
  }
}
