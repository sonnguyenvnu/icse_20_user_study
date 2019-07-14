void preProcess(){
  mOpReorderer.reorderOps(mPendingUpdates);
  final int count=mPendingUpdates.size();
  for (int i=0; i < count; i++) {
    UpdateOp op=mPendingUpdates.get(i);
switch (op.cmd) {
case UpdateOp.ADD:
      applyAdd(op);
    break;
case UpdateOp.REMOVE:
  applyRemove(op);
break;
case UpdateOp.UPDATE:
applyUpdate(op);
break;
case UpdateOp.MOVE:
applyMove(op);
break;
}
if (mOnItemProcessedCallback != null) {
mOnItemProcessedCallback.run();
}
}
mPendingUpdates.clear();
}
