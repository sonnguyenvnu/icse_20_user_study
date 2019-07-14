int findPositionOffset(int position,int firstPostponedItem){
  int count=mPostponedList.size();
  for (int i=firstPostponedItem; i < count; ++i) {
    UpdateOp op=mPostponedList.get(i);
    if (op.cmd == UpdateOp.MOVE) {
      if (op.positionStart == position) {
        position=op.itemCount;
      }
 else {
        if (op.positionStart < position) {
          position--;
        }
        if (op.itemCount <= position) {
          position++;
        }
      }
    }
 else     if (op.positionStart <= position) {
      if (op.cmd == UpdateOp.REMOVE) {
        if (position < op.positionStart + op.itemCount) {
          return -1;
        }
        position-=op.itemCount;
      }
 else       if (op.cmd == UpdateOp.ADD) {
        position+=op.itemCount;
      }
    }
  }
  return position;
}
