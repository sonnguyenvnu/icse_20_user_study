private void findMinMaxChildLayoutPositions(int[] into){
  final int count=mChildHelper.getChildCount();
  if (count == 0) {
    into[0]=NO_POSITION;
    into[1]=NO_POSITION;
    return;
  }
  int minPositionPreLayout=Integer.MAX_VALUE;
  int maxPositionPreLayout=Integer.MIN_VALUE;
  for (int i=0; i < count; ++i) {
    final ViewHolder holder=getChildViewHolderInt(mChildHelper.getChildAt(i));
    if (holder.shouldIgnore()) {
      continue;
    }
    final int pos=holder.getLayoutPosition();
    if (pos < minPositionPreLayout) {
      minPositionPreLayout=pos;
    }
    if (pos > maxPositionPreLayout) {
      maxPositionPreLayout=pos;
    }
  }
  into[0]=minPositionPreLayout;
  into[1]=maxPositionPreLayout;
}
