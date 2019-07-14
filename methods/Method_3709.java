private void applyUpdate(UpdateOp op){
  int tmpStart=op.positionStart;
  int tmpCount=0;
  int tmpEnd=op.positionStart + op.itemCount;
  int type=-1;
  for (int position=op.positionStart; position < tmpEnd; position++) {
    RecyclerView.ViewHolder vh=mCallback.findViewHolder(position);
    if (vh != null || canFindInPreLayout(position)) {
      if (type == POSITION_TYPE_INVISIBLE) {
        UpdateOp newOp=obtainUpdateOp(UpdateOp.UPDATE,tmpStart,tmpCount,op.payload);
        dispatchAndUpdateViewHolders(newOp);
        tmpCount=0;
        tmpStart=position;
      }
      type=POSITION_TYPE_NEW_OR_LAID_OUT;
    }
 else {
      if (type == POSITION_TYPE_NEW_OR_LAID_OUT) {
        UpdateOp newOp=obtainUpdateOp(UpdateOp.UPDATE,tmpStart,tmpCount,op.payload);
        postponeAndUpdateViewHolders(newOp);
        tmpCount=0;
        tmpStart=position;
      }
      type=POSITION_TYPE_INVISIBLE;
    }
    tmpCount++;
  }
  if (tmpCount != op.itemCount) {
    Object payload=op.payload;
    recycleUpdateOp(op);
    op=obtainUpdateOp(UpdateOp.UPDATE,tmpStart,tmpCount,payload);
  }
  if (type == POSITION_TYPE_INVISIBLE) {
    dispatchAndUpdateViewHolders(op);
  }
 else {
    postponeAndUpdateViewHolders(op);
  }
}
