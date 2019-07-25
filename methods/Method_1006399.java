@Override public MappeableContainer add(int begin,int end){
  if (end == begin) {
    return clone();
  }
  if ((begin > end) || (end > (1 << 16))) {
    throw new IllegalArgumentException("Invalid range [" + begin + "," + end + ")");
  }
  MappeableBitmapContainer answer=clone();
  int prevOnesInRange=answer.cardinalityInRange(begin,end);
  BufferUtil.setBitmapRange(answer.bitmap,begin,end);
  answer.updateCardinality(prevOnesInRange,end - begin);
  return answer;
}
