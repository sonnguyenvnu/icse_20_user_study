void dispatchFirstPassAndUpdateViewHolders(UpdateOp op,int offsetStart){
  mCallback.onDispatchFirstPass(op);
switch (op.cmd) {
case UpdateOp.REMOVE:
    mCallback.offsetPositionsForRemovingInvisible(offsetStart,op.itemCount);
  break;
case UpdateOp.UPDATE:
mCallback.markViewHoldersUpdated(offsetStart,op.itemCount,op.payload);
break;
default :
throw new IllegalArgumentException("only remove and update ops can be dispatched" + " in first pass");
}
}
