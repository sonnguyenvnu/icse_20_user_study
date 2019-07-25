@Override public Container iand(final BitmapContainer b2){
  int newCardinality=0;
  for (int k=0; k < this.bitmap.length; ++k) {
    newCardinality+=Long.bitCount(this.bitmap[k] & b2.bitmap[k]);
  }
  if (newCardinality > ArrayContainer.DEFAULT_MAX_SIZE) {
    for (int k=0; k < this.bitmap.length; ++k) {
      this.bitmap[k]=this.bitmap[k] & b2.bitmap[k];
    }
    this.cardinality=newCardinality;
    return this;
  }
  ArrayContainer ac=new ArrayContainer(newCardinality);
  Util.fillArrayAND(ac.content,this.bitmap,b2.bitmap);
  ac.cardinality=newCardinality;
  return ac;
}
