@Override public short select(int j){
  int leftover=j;
  if (BufferUtil.isBackedBySimpleArray(this.bitmap)) {
    long[] b=this.bitmap.array();
    for (int k=0; k < b.length; ++k) {
      int w=Long.bitCount(b[k]);
      if (w > leftover) {
        return (short)(k * 64 + Util.select(b[k],leftover));
      }
      leftover-=w;
    }
  }
 else {
    int len=this.bitmap.limit();
    for (int k=0; k < len; ++k) {
      long X=bitmap.get(k);
      int w=Long.bitCount(X);
      if (w > leftover) {
        return (short)(k * 64 + Util.select(X,leftover));
      }
      leftover-=w;
    }
  }
  throw new IllegalArgumentException("Insufficient cardinality.");
}
