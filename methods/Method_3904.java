void swapMoveRemove(List<AdapterHelper.UpdateOp> list,int movePos,AdapterHelper.UpdateOp moveOp,int removePos,AdapterHelper.UpdateOp removeOp){
  AdapterHelper.UpdateOp extraRm=null;
  boolean revertedMove=false;
  final boolean moveIsBackwards;
  if (moveOp.positionStart < moveOp.itemCount) {
    moveIsBackwards=false;
    if (removeOp.positionStart == moveOp.positionStart && removeOp.itemCount == moveOp.itemCount - moveOp.positionStart) {
      revertedMove=true;
    }
  }
 else {
    moveIsBackwards=true;
    if (removeOp.positionStart == moveOp.itemCount + 1 && removeOp.itemCount == moveOp.positionStart - moveOp.itemCount) {
      revertedMove=true;
    }
  }
  if (moveOp.itemCount < removeOp.positionStart) {
    removeOp.positionStart--;
  }
 else   if (moveOp.itemCount < removeOp.positionStart + removeOp.itemCount) {
    removeOp.itemCount--;
    moveOp.cmd=AdapterHelper.UpdateOp.REMOVE;
    moveOp.itemCount=1;
    if (removeOp.itemCount == 0) {
      list.remove(removePos);
      mCallback.recycleUpdateOp(removeOp);
    }
    return;
  }
  if (moveOp.positionStart <= removeOp.positionStart) {
    removeOp.positionStart++;
  }
 else   if (moveOp.positionStart < removeOp.positionStart + removeOp.itemCount) {
    final int remaining=removeOp.positionStart + removeOp.itemCount - moveOp.positionStart;
    extraRm=mCallback.obtainUpdateOp(AdapterHelper.UpdateOp.REMOVE,moveOp.positionStart + 1,remaining,null);
    removeOp.itemCount=moveOp.positionStart - removeOp.positionStart;
  }
  if (revertedMove) {
    list.set(movePos,removeOp);
    list.remove(removePos);
    mCallback.recycleUpdateOp(moveOp);
    return;
  }
  if (moveIsBackwards) {
    if (extraRm != null) {
      if (moveOp.positionStart > extraRm.positionStart) {
        moveOp.positionStart-=extraRm.itemCount;
      }
      if (moveOp.itemCount > extraRm.positionStart) {
        moveOp.itemCount-=extraRm.itemCount;
      }
    }
    if (moveOp.positionStart > removeOp.positionStart) {
      moveOp.positionStart-=removeOp.itemCount;
    }
    if (moveOp.itemCount > removeOp.positionStart) {
      moveOp.itemCount-=removeOp.itemCount;
    }
  }
 else {
    if (extraRm != null) {
      if (moveOp.positionStart >= extraRm.positionStart) {
        moveOp.positionStart-=extraRm.itemCount;
      }
      if (moveOp.itemCount >= extraRm.positionStart) {
        moveOp.itemCount-=extraRm.itemCount;
      }
    }
    if (moveOp.positionStart >= removeOp.positionStart) {
      moveOp.positionStart-=removeOp.itemCount;
    }
    if (moveOp.itemCount >= removeOp.positionStart) {
      moveOp.itemCount-=removeOp.itemCount;
    }
  }
  list.set(movePos,removeOp);
  if (moveOp.positionStart != moveOp.itemCount) {
    list.set(removePos,moveOp);
  }
 else {
    list.remove(removePos);
  }
  if (extraRm != null) {
    list.add(movePos,extraRm);
  }
}
