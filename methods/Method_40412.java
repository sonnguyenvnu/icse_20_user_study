UnionType newUnion(org.python.indexer.types.Type... types){
  UnionType t=new UnionType(types);
  nativeTypes.add(t);
  return t;
}
