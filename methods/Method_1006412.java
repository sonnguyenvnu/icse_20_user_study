protected MappeableContainer lazyor(MappeableArrayContainer value2){
  MappeableBitmapContainer answer=clone();
  answer.cardinality=-1;
  if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  long[] b=answer.bitmap.array();
  int c=value2.cardinality;
  for (int k=0; k < c; ++k) {
    short v2=value2.content.get(k);
    final int i=toIntUnsigned(v2) >>> 6;
    b[i]|=1L << v2;
  }
  return answer;
}
