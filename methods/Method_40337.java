@Override public Type resolve(Scope s,int tag) throws Exception {
  if (elts.size() == 0) {
    return setType(new ListType());
  }
  ListType listType=null;
  for (  Node elt : elts) {
    if (listType == null) {
      listType=new ListType(resolveExpr(elt,s,tag));
    }
 else {
      listType.add(resolveExpr(elt,s,tag));
    }
  }
  if (listType != null) {
    setType(listType);
  }
  return getType();
}
