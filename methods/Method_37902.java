/** 
 * Conducts a binary search to find the index where Object should be inserted.
 */
protected int findInsertionPoint(final E o,int low,int high){
  while (low <= high) {
    int mid=(low + high) >>> 1;
    int delta=compare(get(mid),o);
    if (delta > 0) {
      high=mid - 1;
    }
 else {
      low=mid + 1;
    }
  }
  return low;
}
