private int getSpanGroupIndex(RecyclerView.Recycler recycler,RecyclerView.State state,int viewPosition){
  if (!state.isPreLayout()) {
    return mSpanSizeLookup.getCachedSpanGroupIndex(viewPosition,mSpanCount);
  }
  final int adapterPosition=recycler.convertPreLayoutPositionToPostLayout(viewPosition);
  if (adapterPosition == -1) {
    if (DEBUG) {
      throw new RuntimeException("Cannot find span group index for position " + viewPosition);
    }
    Log.w(TAG,"Cannot find span size for pre layout position. " + viewPosition);
    return 0;
  }
  return mSpanSizeLookup.getCachedSpanGroupIndex(adapterPosition,mSpanCount);
}
