private void updatePinnedHeader(RecyclerView parent){
  RecyclerView.Adapter adapter=parent.getAdapter();
  if (mAdapter != adapter || mIsAdapterDataChanged) {
    resetPinnedHeader();
    if (mAdapter != null) {
      mAdapter.unregisterAdapterDataObserver(mAdapterDataObserver);
    }
    mAdapter=adapter;
    if (mAdapter != null) {
      mAdapter.registerAdapterDataObserver(mAdapterDataObserver);
    }
  }
}
