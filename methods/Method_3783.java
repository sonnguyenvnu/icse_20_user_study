private int getSpanIndex(RecyclerView.Recycler recycler,RecyclerView.State state,int pos){
  if (!state.isPreLayout()) {
    return mSpanSizeLookup.getCachedSpanIndex(pos,mSpanCount);
  }
  final int cached=mPreLayoutSpanIndexCache.get(pos,-1);
  if (cached != -1) {
    return cached;
  }
  final int adapterPosition=recycler.convertPreLayoutPositionToPostLayout(pos);
  if (adapterPosition == -1) {
    if (DEBUG) {
      throw new RuntimeException("Cannot find span index for pre layout position. It is" + " not cached, not in the adapter. Pos:" + pos);
    }
    Log.w(TAG,"Cannot find span size for pre layout position. It is" + " not cached, not in the adapter. Pos:" + pos);
    return 0;
  }
  return mSpanSizeLookup.getCachedSpanIndex(adapterPosition,mSpanCount);
}
