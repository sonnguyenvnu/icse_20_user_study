public int applyPendingUpdatesToPosition(int position){
  final int size=mPendingUpdates.size();
  for (int i=0; i < size; i++) {
    UpdateOp op=mPendingUpdates.get(i);
switch (op.cmd) {
case UpdateOp.ADD:
      if (op.positionStart <= position) {
        position+=op.itemCount;
      }
    break;
case UpdateOp.REMOVE:
  if (op.positionStart <= position) {
    final int end=op.positionStart + op.itemCount;
    if (end > position) {
      return RecyclerView.NO_POSITION;
    }
    position-=op.itemCount;
  }
break;
case UpdateOp.MOVE:
if (op.positionStart == position) {
position=op.itemCount;
}
 else {
if (op.positionStart < position) {
  position-=1;
}
if (op.itemCount <= position) {
  position+=1;
}
}
break;
}
}
return position;
}
