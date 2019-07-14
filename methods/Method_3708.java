private void applyRemove(UpdateOp op){
  int tmpStart=op.positionStart;
  int tmpCount=0;
  int tmpEnd=op.positionStart + op.itemCount;
  int type=-1;
  for (int position=op.positionStart; position < tmpEnd; position++) {
    boolean typeChanged=false;
    RecyclerView.ViewHolder vh=mCallback.findViewHolder(position);
    if (vh != null || canFindInPreLayout(position)) {
      if (type == POSITION_TYPE_INVISIBLE) {
        UpdateOp newOp=obtainUpdateOp(UpdateOp.REMOVE,tmpStart,tmpCount,null);
        dispatchAndUpdateViewHolders(newOp);
        typeChanged=true;
      }
      type=POSITION_TYPE_NEW_OR_LAID_OUT;
    }
 else {
      if (type == POSITION_TYPE_NEW_OR_LAID_OUT) {
        UpdateOp newOp=obtainUpdateOp(UpdateOp.REMOVE,tmpStart,tmpCount,null);
        postponeAndUpdateViewHolders(newOp);
        typeChanged=true;
      }
      type=POSITION_TYPE_INVISIBLE;
    }
    if (typeChanged) {
      position-=tmpCount;
      tmpEnd-=tmpCount;
      tmpCount=1;
    }
 else {
      tmpCount++;
    }
  }
  if (tmpCount != op.itemCount) {
    recycleUpdateOp(op);
    op=obtainUpdateOp(UpdateOp.REMOVE,tmpStart,tmpCount,null);
  }
  if (type == POSITION_TYPE_INVISIBLE) {
    dispatchAndUpdateViewHolders(op);
  }
 else {
    postponeAndUpdateViewHolders(op);
  }
}
