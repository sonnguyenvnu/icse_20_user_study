@Override public StandardTransactionBuilder vertexCacheSize(int size){
  Preconditions.checkArgument(size >= 0);
  this.vertexCacheSize=size;
  this.indexCacheWeight=size / 2;
  return this;
}
