void reorderOps(List<AdapterHelper.UpdateOp> ops){
  int badMove;
  while ((badMove=getLastMoveOutOfOrder(ops)) != -1) {
    swapMoveOp(ops,badMove,badMove + 1);
  }
}
