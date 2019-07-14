private void addItemsToLastOperation(int numItemsToAdd,EpoxyModel<?> payload){
  lastOp.itemCount+=numItemsToAdd;
  lastOp.addPayload(payload);
}
