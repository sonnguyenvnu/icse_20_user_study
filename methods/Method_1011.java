@Override public synchronized int getSizeInBytes(){
  return mLastBitmapReference == null ? 0 : BitmapUtil.getSizeInBytes(mLastBitmapReference.get());
}
