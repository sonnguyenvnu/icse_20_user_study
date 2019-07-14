/** 
 * Recycles children between given indices.
 * @param startIndex inclusive
 * @param endIndex   exclusive
 */
protected void recycleChildren(RecyclerView.Recycler recycler,int startIndex,int endIndex){
  if (startIndex == endIndex) {
    return;
  }
  if (DEBUG) {
    Log.d(TAG,"Recycling " + Math.abs(startIndex - endIndex) + " items");
  }
  if (endIndex > startIndex) {
    for (int i=endIndex - 1; i >= startIndex; i--) {
      removeAndRecycleViewAt(i,recycler);
    }
  }
 else {
    for (int i=startIndex; i > endIndex; i--) {
      removeAndRecycleViewAt(i,recycler);
    }
  }
}
