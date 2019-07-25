@Override public Container remove(int begin,int end){
  if (end == begin) {
    return clone();
  }
  if ((begin > end) || (end > (1 << 16))) {
    throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
  }
  BitmapContainer answer=clone();
  int prevOnesInRange=answer.cardinalityInRange(begin,end);
  Util.resetBitmapRange(answer.bitmap,begin,end);
  answer.updateCardinality(prevOnesInRange,0);
  if (answer.getCardinality() <= ArrayContainer.DEFAULT_MAX_SIZE) {
    return answer.toArrayContainer();
  }
  return answer;
}
