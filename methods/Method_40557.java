static public Type remove(Type t1,Type t2){
  if (t1 instanceof UnionType) {
    Set<Type> types=new HashSet<Type>(((UnionType)t1).getTypes());
    types.remove(t2);
    return UnionType.newUnion(types);
  }
 else   if (t1 == t2) {
    return Indexer.idx.builtins.unknown;
  }
 else {
    return t1;
  }
}
