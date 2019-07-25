protected MappeableContainer ilazyor(MappeableBitmapContainer x){
  if (BufferUtil.isBackedBySimpleArray(x.bitmap)) {
    long[] b=this.bitmap.array();
    long[] b2=x.bitmap.array();
    for (int k=0; k < b.length; k++) {
      b[k]|=b2[k];
    }
  }
 else {
    final int m=this.bitmap.limit();
    for (int k=0; k < m; k++) {
      this.bitmap.put(k,this.bitmap.get(k) | x.bitmap.get(k));
    }
  }
  this.cardinality=-1;
  return this;
}
