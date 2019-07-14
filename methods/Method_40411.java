ListType newList(org.python.indexer.types.Type type){
  ListType t=new ListType(type);
  nativeTypes.add(t);
  return t;
}
