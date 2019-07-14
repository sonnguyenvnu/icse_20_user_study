private synchronized CloseableReference<Bitmap> detachBitmapReference(){
  CloseableReference<Bitmap> reference=mBitmapReference;
  mBitmapReference=null;
  mBitmap=null;
  return reference;
}
