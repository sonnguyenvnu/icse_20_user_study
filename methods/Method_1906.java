private void updateBitmapFrameCache(BitmapFrameCache bitmapFrameCache){
  if (mBitmapFrameCacheChangedListener != null) {
    mBitmapFrameCacheChangedListener.onBitmapFrameCacheChanged(bitmapFrameCache);
  }
}
