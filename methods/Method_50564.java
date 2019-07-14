private Bitmap computeSuitedBitmapSize(Bitmap bitmap){
  int suitedWidth=width;
  int suitedHeight=height;
  if ((float)bitmap.getWidth() / (float)width > (float)bitmap.getHeight() / (float)height) {
    suitedWidth=(int)(((float)bitmap.getWidth() / (float)bitmap.getHeight()) * (float)suitedHeight);
  }
 else {
    suitedHeight=(int)((float)suitedWidth / ((float)bitmap.getWidth() / (float)bitmap.getHeight()));
  }
  bitmap=Bitmap.createScaledBitmap(bitmap,suitedWidth,suitedHeight,true);
  bitmap=Bitmap.createBitmap(bitmap,bitmap.getWidth() / 2 - width / 2,bitmap.getHeight() / 2 - height / 2,width,height);
  return bitmap;
}
