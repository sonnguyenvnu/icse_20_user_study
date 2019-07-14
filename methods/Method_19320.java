/** 
 * Remove operation is not supported in case of circular recycler unless it's a removal if all items because indexes universe gets messed.
 */
@GuardedBy("this") private void assertNoRemoveOperationIfCircular(int removeCount){
  if (mIsCircular && !mComponentTreeHolders.isEmpty() && mComponentTreeHolders.size() != removeCount) {
    throw new UnsupportedOperationException("Circular lists do not support insert operation");
  }
}
