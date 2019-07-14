private int updatePositionWithPostponed(int pos,int cmd){
  final int count=mPostponedList.size();
  for (int i=count - 1; i >= 0; i--) {
    UpdateOp postponed=mPostponedList.get(i);
    if (postponed.cmd == UpdateOp.MOVE) {
      int start, end;
      if (postponed.positionStart < postponed.itemCount) {
        start=postponed.positionStart;
        end=postponed.itemCount;
      }
 else {
        start=postponed.itemCount;
        end=postponed.positionStart;
      }
      if (pos >= start && pos <= end) {
        if (start == postponed.positionStart) {
          if (cmd == UpdateOp.ADD) {
            postponed.itemCount++;
          }
 else           if (cmd == UpdateOp.REMOVE) {
            postponed.itemCount--;
          }
          pos++;
        }
 else {
          if (cmd == UpdateOp.ADD) {
            postponed.positionStart++;
          }
 else           if (cmd == UpdateOp.REMOVE) {
            postponed.positionStart--;
          }
          pos--;
        }
      }
 else       if (pos < postponed.positionStart) {
        if (cmd == UpdateOp.ADD) {
          postponed.positionStart++;
          postponed.itemCount++;
        }
 else         if (cmd == UpdateOp.REMOVE) {
          postponed.positionStart--;
          postponed.itemCount--;
        }
      }
    }
 else {
      if (postponed.positionStart <= pos) {
        if (postponed.cmd == UpdateOp.ADD) {
          pos-=postponed.itemCount;
        }
 else         if (postponed.cmd == UpdateOp.REMOVE) {
          pos+=postponed.itemCount;
        }
      }
 else {
        if (cmd == UpdateOp.ADD) {
          postponed.positionStart++;
        }
 else         if (cmd == UpdateOp.REMOVE) {
          postponed.positionStart--;
        }
      }
    }
    if (DEBUG) {
      Log.d(TAG,"dispath (step" + i + ")");
      Log.d(TAG,"postponed state:" + i + ", pos:" + pos);
      for (      UpdateOp updateOp : mPostponedList) {
        Log.d(TAG,updateOp.toString());
      }
      Log.d(TAG,"----");
    }
  }
  for (int i=mPostponedList.size() - 1; i >= 0; i--) {
    UpdateOp op=mPostponedList.get(i);
    if (op.cmd == UpdateOp.MOVE) {
      if (op.itemCount == op.positionStart || op.itemCount < 0) {
        mPostponedList.remove(i);
        recycleUpdateOp(op);
      }
    }
 else     if (op.itemCount <= 0) {
      mPostponedList.remove(i);
      recycleUpdateOp(op);
    }
  }
  return pos;
}
