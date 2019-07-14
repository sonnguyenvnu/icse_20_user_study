private void swapMoveOp(List<AdapterHelper.UpdateOp> list,int badMove,int next){
  final AdapterHelper.UpdateOp moveOp=list.get(badMove);
  final AdapterHelper.UpdateOp nextOp=list.get(next);
switch (nextOp.cmd) {
case AdapterHelper.UpdateOp.REMOVE:
    swapMoveRemove(list,badMove,moveOp,next,nextOp);
  break;
case AdapterHelper.UpdateOp.ADD:
swapMoveAdd(list,badMove,moveOp,next,nextOp);
break;
case AdapterHelper.UpdateOp.UPDATE:
swapMoveUpdate(list,badMove,moveOp,next,nextOp);
break;
}
}
