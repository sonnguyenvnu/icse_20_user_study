private boolean updateAnchorFromChildren(RecyclerView.State state,AnchorInfo anchorInfo){
  anchorInfo.mPosition=mLastLayoutFromEnd ? findLastReferenceChildPosition(state.getItemCount()) : findFirstReferenceChildPosition(state.getItemCount());
  anchorInfo.mOffset=INVALID_OFFSET;
  return true;
}
