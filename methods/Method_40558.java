static public Type newUnion(Collection<Type> types){
  Type t=Indexer.idx.builtins.unknown;
  for (  Type nt : types) {
    t=union(t,nt);
  }
  return t;
}
