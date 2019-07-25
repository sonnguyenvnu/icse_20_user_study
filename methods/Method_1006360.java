protected Container lazyor(ArrayContainer value2){
  BitmapContainer answer=this.clone();
  answer.cardinality=-1;
  int c=value2.cardinality;
  for (int k=0; k < c; ++k) {
    short v=value2.content[k];
    final int i=Util.toIntUnsigned(v) >>> 6;
    answer.bitmap[i]|=(1L << v);
  }
  return answer;
}
