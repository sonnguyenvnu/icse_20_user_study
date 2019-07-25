@Override public MappeableContainer iremove(int begin,int end){
  if (end == begin) {
    return this;
  }
  if ((begin > end) || (end > (1 << 16))) {
    throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
  }
  int prevOnesInRange=cardinalityInRange(begin,end);
  BufferUtil.resetBitmapRange(bitmap,begin,end);
  updateCardinality(prevOnesInRange,0);
  if (getCardinality() < MappeableArrayContainer.DEFAULT_MAX_SIZE) {
    return toArrayContainer();
  }
  return this;
}
