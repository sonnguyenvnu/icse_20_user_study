public static Type makeUnion(List<Binding> bs){
  Type t=Type.UNKNOWN;
  for (  Binding b : bs) {
    t=UnionType.union(t,b.type);
  }
  return t;
}
