FunType newFunc(org.python.indexer.types.Type type){
  FunType t=new FunType(Indexer.idx.builtins.unknown,type);
  nativeTypes.add(t);
  return t;
}
