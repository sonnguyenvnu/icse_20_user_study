@Override public MappeableContainer iand(final MappeableBitmapContainer b2){
  if (BufferUtil.isBackedBySimpleArray(this.bitmap) && BufferUtil.isBackedBySimpleArray(b2.bitmap)) {
    int newCardinality=0;
    long[] tb=this.bitmap.array();
    long[] tb2=b2.bitmap.array();
    int len=this.bitmap.limit();
    for (int k=0; k < len; ++k) {
      newCardinality+=Long.bitCount(tb[k] & tb2[k]);
    }
    if (newCardinality > MappeableArrayContainer.DEFAULT_MAX_SIZE) {
      for (int k=0; k < len; ++k) {
        tb[k]&=tb2[k];
      }
      this.cardinality=newCardinality;
      return this;
    }
    final MappeableArrayContainer ac=new MappeableArrayContainer(newCardinality);
    BufferUtil.fillArrayAND(ac.content.array(),this.bitmap,b2.bitmap);
    ac.cardinality=newCardinality;
    return ac;
  }
 else {
    int newCardinality=0;
    int len=this.bitmap.limit();
    for (int k=0; k < len; ++k) {
      newCardinality+=Long.bitCount(this.bitmap.get(k) & b2.bitmap.get(k));
    }
    if (newCardinality > MappeableArrayContainer.DEFAULT_MAX_SIZE) {
      for (int k=0; k < len; ++k) {
        this.bitmap.put(k,this.bitmap.get(k) & b2.bitmap.get(k));
      }
      this.cardinality=newCardinality;
      return this;
    }
    final MappeableArrayContainer ac=new MappeableArrayContainer(newCardinality);
    BufferUtil.fillArrayAND(ac.content.array(),this.bitmap,b2.bitmap);
    ac.cardinality=newCardinality;
    return ac;
  }
}
