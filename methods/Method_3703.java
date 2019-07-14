AdapterHelper addUpdateOp(UpdateOp... ops){
  Collections.addAll(mPendingUpdates,ops);
  return this;
}
