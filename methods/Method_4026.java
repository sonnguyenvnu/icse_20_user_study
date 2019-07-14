/** 
 * Called when the instance of a  {@link RecyclerView} is detached.
 */
private void destroyCallbacks(){
  mRecyclerView.removeOnScrollListener(mScrollListener);
  mRecyclerView.setOnFlingListener(null);
}
