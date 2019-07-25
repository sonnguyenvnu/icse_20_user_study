@Override public MapQuery<K,V,R> entry(){
  checkOnEachPublicOperation.checkOnEachPublicOperation();
  return entryPresent() ? this : null;
}
