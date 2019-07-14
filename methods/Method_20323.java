void remove(int startPosition,int itemCount){
  numRemovals+=itemCount;
  boolean batchWithLast=false;
  if (isLastOp(REMOVE)) {
    if (lastOp.positionStart == startPosition) {
      batchWithLast=true;
    }
 else     if (lastOp.isAfter(startPosition) && startPosition + itemCount >= lastOp.positionStart) {
      lastOp.positionStart=startPosition;
      batchWithLast=true;
    }
  }
  if (batchWithLast) {
    addItemsToLastOperation(itemCount,null);
  }
 else {
    numRemovalBatches++;
    addNewOperation(REMOVE,startPosition,itemCount);
  }
}
