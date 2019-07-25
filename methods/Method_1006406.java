@Override public boolean intersects(MappeableBitmapContainer value2){
  if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(value2.bitmap)) {
    long[] tb=this.bitmap.array();
    long[] v2b=value2.bitmap.array();
    int len=this.bitmap.limit();
    for (int k=0; k < len; ++k) {
      if ((tb[k] & v2b[k]) != 0) {
        return true;
      }
    }
  }
 else {
    int len=this.bitmap.limit();
    for (int k=0; k < len; ++k) {
      if ((this.bitmap.get(k) & value2.bitmap.get(k)) != 0) {
        return true;
      }
    }
  }
  return false;
}
