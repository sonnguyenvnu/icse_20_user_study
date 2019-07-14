private void destroyCallbacks(){
  mRecyclerView.removeItemDecoration(this);
  mRecyclerView.removeOnItemTouchListener(this);
  mRecyclerView.removeOnScrollListener(mOnScrollListener);
  cancelHide();
}
