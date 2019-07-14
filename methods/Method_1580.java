@VisibleForTesting private Bitmap alloc(int size){
  mPoolStatsTracker.onAlloc(size);
  return Bitmap.createBitmap(1,size,Bitmap.Config.ALPHA_8);
}
