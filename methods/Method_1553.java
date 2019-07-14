protected boolean isReusable(@Nullable Bitmap bitmap){
  if (bitmap == null) {
    return false;
  }
  if (bitmap.isRecycled()) {
    FLog.wtf(TAG,"Cannot reuse a recycled bitmap: %s",bitmap);
    return false;
  }
  if (!bitmap.isMutable()) {
    FLog.wtf(TAG,"Cannot reuse an immutable bitmap: %s",bitmap);
    return false;
  }
  return true;
}
