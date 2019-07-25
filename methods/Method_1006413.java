protected MappeableContainer lazyor(MappeableBitmapContainer x){
  MappeableBitmapContainer answer=new MappeableBitmapContainer();
  answer.cardinality=-1;
  if (!BufferUtil.isBackedBySimpleArray(answer.bitmap)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  long[] b=answer.bitmap.array();
  for (int k=0; k < b.length; k++) {
    b[k]=this.bitmap.get(k) | x.bitmap.get(k);
  }
  return answer;
}
