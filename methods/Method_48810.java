@Override public void emit(final OK key,final OV value){
  this.reduceQueue.add(new KeyValue<>(key,value));
}
