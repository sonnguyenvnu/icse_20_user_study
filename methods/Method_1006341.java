@Override public Container xor(final ArrayContainer value2){
  final ArrayContainer value1=this;
  final int totalCardinality=value1.getCardinality() + value2.getCardinality();
  if (totalCardinality > DEFAULT_MAX_SIZE) {
    BitmapContainer bc=new BitmapContainer();
    for (int k=0; k < value2.cardinality; ++k) {
      short v=value2.content[k];
      final int i=toIntUnsigned(v) >>> 6;
      bc.bitmap[i]^=(1L << v);
    }
    for (int k=0; k < this.cardinality; ++k) {
      short v=this.content[k];
      final int i=toIntUnsigned(v) >>> 6;
      bc.bitmap[i]^=(1L << v);
    }
    bc.cardinality=0;
    for (    long k : bc.bitmap) {
      bc.cardinality+=Long.bitCount(k);
    }
    if (bc.cardinality <= DEFAULT_MAX_SIZE) {
      return bc.toArrayContainer();
    }
    return bc;
  }
  ArrayContainer answer=new ArrayContainer(totalCardinality);
  answer.cardinality=Util.unsignedExclusiveUnion2by2(value1.content,value1.getCardinality(),value2.content,value2.getCardinality(),answer.content);
  return answer;
}
