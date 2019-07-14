private void ensureAnchorIsInCorrectSpan(RecyclerView.Recycler recycler,RecyclerView.State state,AnchorInfo anchorInfo,int itemDirection){
  final boolean layingOutInPrimaryDirection=itemDirection == LayoutState.ITEM_DIRECTION_TAIL;
  int span=getSpanIndex(recycler,state,anchorInfo.mPosition);
  if (layingOutInPrimaryDirection) {
    while (span > 0 && anchorInfo.mPosition > 0) {
      anchorInfo.mPosition--;
      span=getSpanIndex(recycler,state,anchorInfo.mPosition);
    }
  }
 else {
    final int indexLimit=state.getItemCount() - 1;
    int pos=anchorInfo.mPosition;
    int bestSpan=span;
    while (pos < indexLimit) {
      int next=getSpanIndex(recycler,state,pos + 1);
      if (next > bestSpan) {
        pos+=1;
        bestSpan=next;
      }
 else {
        break;
      }
    }
    anchorInfo.mPosition=pos;
  }
}
