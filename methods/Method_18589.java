private static void releaseScrapTouchDelegatesArray(SparseArrayCompat<InnerTouchDelegate> sparseArray){
  sInnerTouchDelegateScrapArrayPool.release(sparseArray);
}
