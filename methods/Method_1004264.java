@NotNull @Override public MapQuery<K,V,?> context(){
  checkOnEachPublicOperation.checkOnEachPublicOperation();
  return q;
}
