private synchronized void prepareTempBitmapForThisSize(int width,int height){
  if (mTempBitmap != null && (mTempBitmap.getWidth() < width || mTempBitmap.getHeight() < height)) {
    clearTempBitmap();
  }
  if (mTempBitmap == null) {
    mTempBitmap=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
  }
  mTempBitmap.eraseColor(Color.TRANSPARENT);
}
