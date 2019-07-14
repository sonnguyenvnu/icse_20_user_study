private void postponeAndUpdateViewHolders(UpdateOp op){
  if (DEBUG) {
    Log.d(TAG,"postponing " + op);
  }
  mPostponedList.add(op);
switch (op.cmd) {
case UpdateOp.ADD:
    mCallback.offsetPositionsForAdd(op.positionStart,op.itemCount);
  break;
case UpdateOp.MOVE:
mCallback.offsetPositionsForMove(op.positionStart,op.itemCount);
break;
case UpdateOp.REMOVE:
mCallback.offsetPositionsForRemovingLaidOutOrNewView(op.positionStart,op.itemCount);
break;
case UpdateOp.UPDATE:
mCallback.markViewHoldersUpdated(op.positionStart,op.itemCount,op.payload);
break;
default :
throw new IllegalArgumentException("Unknown update op type for " + op);
}
}
