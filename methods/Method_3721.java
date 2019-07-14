/** 
 * Skips pre-processing and applies all updates in one pass.
 */
void consumeUpdatesInOnePass(){
  consumePostponedUpdates();
  final int count=mPendingUpdates.size();
  for (int i=0; i < count; i++) {
    UpdateOp op=mPendingUpdates.get(i);
switch (op.cmd) {
case UpdateOp.ADD:
      mCallback.onDispatchSecondPass(op);
    mCallback.offsetPositionsForAdd(op.positionStart,op.itemCount);
  break;
case UpdateOp.REMOVE:
mCallback.onDispatchSecondPass(op);
mCallback.offsetPositionsForRemovingInvisible(op.positionStart,op.itemCount);
break;
case UpdateOp.UPDATE:
mCallback.onDispatchSecondPass(op);
mCallback.markViewHoldersUpdated(op.positionStart,op.itemCount,op.payload);
break;
case UpdateOp.MOVE:
mCallback.onDispatchSecondPass(op);
mCallback.offsetPositionsForMove(op.positionStart,op.itemCount);
break;
}
if (mOnItemProcessedCallback != null) {
mOnItemProcessedCallback.run();
}
}
recycleUpdateOpsAndClearList(mPendingUpdates);
mExistingUpdateTypes=0;
}
