@UiThread private void applyBatch(AsyncBatch batch){
synchronized (this) {
    for (int i=0, size=batch.mOperations.size(); i < size; i++) {
      final AsyncOperation operation=batch.mOperations.get(i);
switch (operation.mOperation) {
case Operation.INSERT:
        applyAsyncInsert((AsyncInsertOperation)operation);
      break;
case Operation.UPDATE:
    final AsyncUpdateOperation updateOperation=(AsyncUpdateOperation)operation;
  updateItemAt(updateOperation.mPosition,updateOperation.mRenderInfo);
break;
case Operation.UPDATE_RANGE:
final AsyncUpdateRangeOperation updateRangeOperation=(AsyncUpdateRangeOperation)operation;
updateRangeAt(updateRangeOperation.mPosition,updateRangeOperation.mRenderInfos);
break;
case Operation.REMOVE:
removeItemAt(((AsyncRemoveOperation)operation).mPosition);
break;
case Operation.REMOVE_RANGE:
final AsyncRemoveRangeOperation removeRangeOperation=(AsyncRemoveRangeOperation)operation;
removeRangeAt(removeRangeOperation.mPosition,removeRangeOperation.mCount);
break;
case Operation.MOVE:
final AsyncMoveOperation moveOperation=(AsyncMoveOperation)operation;
moveItem(moveOperation.mFromPosition,moveOperation.mToPosition);
break;
default :
throw new RuntimeException("Unhandled operation type: " + operation.mOperation);
}
}
}
batch.mChangeSetCompleteCallback.onDataBound();
mDataRenderedCallbacks.addLast(batch.mChangeSetCompleteCallback);
maybeDispatchDataRendered();
}
