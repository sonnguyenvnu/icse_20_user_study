private int getOffset(int index){
  if (index < 0) {
    return -1;
  }
  final int limit=mCallback.getChildCount();
  int offset=index;
  while (offset < limit) {
    final int removedBefore=mBucket.countOnesBefore(offset);
    final int diff=index - (offset - removedBefore);
    if (diff == 0) {
      while (mBucket.get(offset)) {
        offset++;
      }
      return offset;
    }
 else {
      offset+=diff;
    }
  }
  return -1;
}
