@Override public MappeableContainer remove(int begin,int end){
  if (end == begin) {
    return clone();
  }
  if ((begin > end) || (end > (1 << 16))) {
    throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
  }
  MappeableBitmapContainer answer=clone();
  int prevOnesInRange=answer.cardinalityInRange(begin,end);
  BufferUtil.resetBitmapRange(answer.bitmap,begin,end);
  answer.updateCardinality(prevOnesInRange,0);
  if (answer.getCardinality() < MappeableArrayContainer.DEFAULT_MAX_SIZE) {
    return answer.toArrayContainer();
  }
  return answer;
}
