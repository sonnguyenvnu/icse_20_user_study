/** 
 * Schedule a prefetch immediately after the current traversal.
 */
void postFromTraversal(RecyclerView recyclerView,int prefetchDx,int prefetchDy){
  if (recyclerView.isAttachedToWindow()) {
    if (RecyclerView.DEBUG && !mRecyclerViews.contains(recyclerView)) {
      throw new IllegalStateException("attempting to post unregistered view!");
    }
    if (mPostTimeNs == 0) {
      mPostTimeNs=recyclerView.getNanoTime();
      recyclerView.post(this);
    }
  }
  recyclerView.mPrefetchRegistry.setPrefetchVector(prefetchDx,prefetchDy);
}
