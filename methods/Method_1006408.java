@Override public MappeableContainer ior(final MappeableBitmapContainer b2){
  if (!BufferUtil.isBackedBySimpleArray(bitmap)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  long[] b=this.bitmap.array();
  this.cardinality=0;
  if (BufferUtil.isBackedBySimpleArray(b2.bitmap)) {
    long[] b2Arr=b2.bitmap.array();
    int len=this.bitmap.limit();
    for (int k=0; k < len; k++) {
      long w=b[k] | b2Arr[k];
      b[k]=w;
      this.cardinality+=Long.bitCount(w);
    }
    if (isFull()) {
      return MappeableRunContainer.full();
    }
    return this;
  }
  int len=this.bitmap.limit();
  for (int k=0; k < len; k++) {
    long w=b[k] | b2.bitmap.get(k);
    b[k]=w;
    this.cardinality+=Long.bitCount(w);
  }
  if (isFull()) {
    return MappeableRunContainer.full();
  }
  return this;
}
