private boolean canFindInPreLayout(int position){
  final int count=mPostponedList.size();
  for (int i=0; i < count; i++) {
    UpdateOp op=mPostponedList.get(i);
    if (op.cmd == UpdateOp.MOVE) {
      if (findPositionOffset(op.itemCount,i + 1) == position) {
        return true;
      }
    }
 else     if (op.cmd == UpdateOp.ADD) {
      final int end=op.positionStart + op.itemCount;
      for (int pos=op.positionStart; pos < end; pos++) {
        if (findPositionOffset(pos,i + 1) == position) {
          return true;
        }
      }
    }
  }
  return false;
}
