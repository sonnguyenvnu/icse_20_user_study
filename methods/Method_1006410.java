@Override public MappeableContainer ixor(final MappeableArrayContainer value2){
  if (!BufferUtil.isBackedBySimpleArray(bitmap)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  long[] b=bitmap.array();
  if (BufferUtil.isBackedBySimpleArray(value2.content)) {
    short[] v2=value2.content.array();
    int c=value2.cardinality;
    for (int k=0; k < c; ++k) {
      short vc=v2[k];
      long mask=1L << v2[k];
      final int index=toIntUnsigned(vc) >>> 6;
      long ba=b[index];
      this.cardinality+=1 - 2 * ((ba & mask) >>> vc);
      b[index]=ba ^ mask;
    }
  }
 else {
    int c=value2.cardinality;
    for (int k=0; k < c; ++k) {
      short v2=value2.content.get(k);
      long mask=1L << v2;
      final int index=toIntUnsigned(v2) >>> 6;
      long ba=b[index];
      this.cardinality+=1 - 2 * ((ba & mask) >>> v2);
      b[index]=ba ^ mask;
    }
  }
  if (this.cardinality <= MappeableArrayContainer.DEFAULT_MAX_SIZE) {
    return this.toArrayContainer();
  }
  return this;
}
