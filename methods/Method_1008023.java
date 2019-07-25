@Override public String stringify(Object object){
  CollectionUtils.ensureNoSelfReferences(object);
  return super.stringify(object);
}
