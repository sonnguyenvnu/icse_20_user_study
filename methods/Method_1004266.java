@NotNull @Override public MapQuery<K,V,R> context(){
  checkOnEachPublicOperation.checkOnEachPublicOperation();
  return this;
}
