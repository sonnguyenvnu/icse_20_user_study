private void soConsumerIndex(long v){
  UNSAFE.putOrderedLong(this,C_INDEX_OFFSET,v);
}
