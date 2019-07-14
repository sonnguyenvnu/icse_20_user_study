@Override public Type resolve(Scope s,int tag) throws Exception {
  if (elts.size() == 0) {
    return setType(new ListType());
  }
  ListType listType=new ListType();
  for (  Node elt : elts) {
    listType.add(resolveExpr(elt,s,tag));
  }
  setType(listType);
  return getType();
}
