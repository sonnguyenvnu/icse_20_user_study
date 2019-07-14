void update(final int indexToChange,EpoxyModel<?> payload){
  if (isLastOp(UPDATE)) {
    if (lastOp.positionStart == indexToChange + 1) {
      addItemsToLastOperation(1,payload);
      lastOp.positionStart=indexToChange;
    }
 else     if (lastOp.positionEnd() == indexToChange) {
      addItemsToLastOperation(1,payload);
    }
 else     if (lastOp.contains(indexToChange)) {
      addItemsToLastOperation(0,payload);
    }
 else {
      addNewOperation(UPDATE,indexToChange,1,payload);
    }
  }
 else {
    addNewOperation(UPDATE,indexToChange,1,payload);
  }
}
