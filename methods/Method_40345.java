@Override public Type resolve(Scope s,int tag) throws Exception {
  TupleType thisType=new TupleType();
  for (  Node e : elts) {
    thisType.add(resolveExpr(e,s,tag));
  }
  return setType(thisType);
}
