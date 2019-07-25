@Override public MappeableContainer xor(final MappeableArrayContainer value2){
  final MappeableArrayContainer value1=this;
  final int totalCardinality=value1.getCardinality() + value2.getCardinality();
  if (totalCardinality > DEFAULT_MAX_SIZE) {
    final MappeableBitmapContainer bc=new MappeableBitmapContainer();
    if (!BufferUtil.isBackedBySimpleArray(bc.bitmap)) {
      throw new RuntimeException("Should not happen. Internal bug.");
    }
    long[] bitArray=bc.bitmap.array();
    if (BufferUtil.isBackedBySimpleArray(value2.content)) {
      short[] sarray=value2.content.array();
      for (int k=0; k < value2.cardinality; ++k) {
        short v=sarray[k];
        final int i=toIntUnsigned(v) >>> 6;
        bitArray[i]^=(1L << v);
      }
    }
 else {
      for (int k=0; k < value2.cardinality; ++k) {
        short v2=value2.content.get(k);
        final int i=toIntUnsigned(v2) >>> 6;
        bitArray[i]^=(1L << v2);
      }
    }
    if (BufferUtil.isBackedBySimpleArray(this.content)) {
      short[] sarray=this.content.array();
      for (int k=0; k < this.cardinality; ++k) {
        short v=sarray[k];
        final int i=toIntUnsigned(v) >>> 6;
        bitArray[i]^=(1L << v);
      }
    }
 else {
      for (int k=0; k < this.cardinality; ++k) {
        short v=this.content.get(k);
        final int i=toIntUnsigned(v) >>> 6;
        bitArray[i]^=(1L << v);
      }
    }
    bc.cardinality=0;
    int len=bc.bitmap.limit();
    for (int index=0; index < len; ++index) {
      bc.cardinality+=Long.bitCount(bitArray[index]);
    }
    if (bc.cardinality <= DEFAULT_MAX_SIZE) {
      return bc.toArrayContainer();
    }
    return bc;
  }
  final MappeableArrayContainer answer=new MappeableArrayContainer(totalCardinality);
  if (BufferUtil.isBackedBySimpleArray(value1.content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
    answer.cardinality=org.roaringbitmap.Util.unsignedExclusiveUnion2by2(value1.content.array(),value1.getCardinality(),value2.content.array(),value2.getCardinality(),answer.content.array());
  }
 else {
    answer.cardinality=BufferUtil.unsignedExclusiveUnion2by2(value1.content,value1.getCardinality(),value2.content,value2.getCardinality(),answer.content.array());
  }
  return answer;
}
