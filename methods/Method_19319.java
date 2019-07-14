/** 
 * Insert operation is not supported in case of circular recycler unless it is initial insert because the indexes universe gets messed.
 */
private void assertNoInsertOperationIfCircular(){
  if (mIsCircular && !mComponentTreeHolders.isEmpty()) {
    throw new UnsupportedOperationException("Circular lists do not support insert operation");
  }
}
