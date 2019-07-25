@Override public MappeableBitmapContainer ior(final MappeableArrayContainer value2){
  if (!BufferUtil.isBackedBySimpleArray(this.bitmap)) {
    throw new RuntimeException("Should not happen. Internal bug.");
  }
  long[] b=this.bitmap.array();
  if (BufferUtil.isBackedBySimpleArray(value2.content)) {
    short[] v2=value2.content.array();
    int c=value2.cardinality;
    for (int k=0; k < c; ++k) {
      final int i=toIntUnsigned(v2[k]) >>> 6;
      long bef=b[i];
      long aft=bef | (1L << v2[k]);
      b[i]=aft;
      if (USE_BRANCHLESS) {
        cardinality+=(bef - aft) >>> 63;
      }
 else {
        if (aft != bef) {
          cardinality++;
        }
      }
    }
    return this;
  }
  int c=value2.cardinality;
  for (int k=0; k < c; ++k) {
    short v2=value2.content.get(k);
    final int i=toIntUnsigned(v2) >>> 6;
    long bef=b[i];
    long aft=bef | (1L << v2);
    b[i]=aft;
    if (USE_BRANCHLESS) {
      cardinality+=(bef - aft) >>> 63;
    }
 else {
      if (aft != bef) {
        cardinality++;
      }
    }
  }
  return this;
}
