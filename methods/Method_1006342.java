protected Container lazyor(ArrayContainer value2){
  final ArrayContainer value1=this;
  int totalCardinality=value1.getCardinality() + value2.getCardinality();
  if (totalCardinality > ARRAY_LAZY_LOWERBOUND) {
    BitmapContainer bc=new BitmapContainer();
    for (int k=0; k < value2.cardinality; ++k) {
      short v=value2.content[k];
      final int i=toIntUnsigned(v) >>> 6;
      bc.bitmap[i]|=(1L << v);
    }
    for (int k=0; k < this.cardinality; ++k) {
      short v=this.content[k];
      final int i=toIntUnsigned(v) >>> 6;
      bc.bitmap[i]|=(1L << v);
    }
    bc.cardinality=-1;
    return bc;
  }
  ArrayContainer answer=new ArrayContainer(totalCardinality);
  answer.cardinality=Util.unsignedUnion2by2(value1.content,0,value1.getCardinality(),value2.content,0,value2.getCardinality(),answer.content);
  return answer;
}
