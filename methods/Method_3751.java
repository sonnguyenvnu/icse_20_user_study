public void attachToRecyclerView(@Nullable RecyclerView recyclerView){
  if (mRecyclerView == recyclerView) {
    return;
  }
  if (mRecyclerView != null) {
    destroyCallbacks();
  }
  mRecyclerView=recyclerView;
  if (mRecyclerView != null) {
    setupCallbacks();
  }
}
