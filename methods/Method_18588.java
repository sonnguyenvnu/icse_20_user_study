private static SparseArrayCompat<InnerTouchDelegate> acquireScrapTouchDelegatesArray(){
  SparseArrayCompat<InnerTouchDelegate> sparseArray=sInnerTouchDelegateScrapArrayPool.acquire();
  if (sparseArray == null) {
    sparseArray=new SparseArrayCompat<>(4);
  }
  return sparseArray;
}
