@Override public MappeableContainer ior(final MappeableArrayContainer value2){
  final int totalCardinality=getCardinality() + value2.getCardinality();
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
        bitArray[i]|=(1L << v);
      }
    }
 else {
      for (int k=0; k < value2.cardinality; ++k) {
        short v2=value2.content.get(k);
        final int i=toIntUnsigned(v2) >>> 6;
        bitArray[i]|=(1L << v2);
      }
    }
    if (BufferUtil.isBackedBySimpleArray(content)) {
      short[] sarray=content.array();
      for (int k=0; k < cardinality; ++k) {
        short v=sarray[k];
        final int i=toIntUnsigned(v) >>> 6;
        bitArray[i]|=(1L << v);
      }
    }
 else {
      for (int k=0; k < cardinality; ++k) {
        short v=content.get(k);
        final int i=toIntUnsigned(v) >>> 6;
        bitArray[i]|=(1L << v);
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
 else     if (bc.isFull()) {
      return MappeableRunContainer.full();
    }
    return bc;
  }
  if (totalCardinality >= content.limit()) {
    int newCapacity=calculateCapacity(totalCardinality);
    ShortBuffer destination=ShortBuffer.allocate(newCapacity);
    if (BufferUtil.isBackedBySimpleArray(content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
      cardinality=Util.unsignedUnion2by2(content.array(),0,cardinality,value2.content.array(),0,value2.cardinality,destination.array());
    }
 else {
      cardinality=BufferUtil.unsignedUnion2by2(content,0,cardinality,value2.content,0,value2.cardinality,destination.array());
    }
    this.content=destination;
  }
 else {
    BufferUtil.arraycopy(content,0,content,value2.cardinality,cardinality);
    if (BufferUtil.isBackedBySimpleArray(content) && BufferUtil.isBackedBySimpleArray(value2.content)) {
      cardinality=Util.unsignedUnion2by2(content.array(),value2.cardinality,cardinality,value2.content.array(),0,value2.cardinality,content.array());
    }
 else {
      cardinality=BufferUtil.unsignedUnion2by2(content,value2.cardinality,cardinality,value2.content,0,value2.cardinality,content.array());
    }
  }
  return this;
}
