@NotNull @Override public Type transform(State s){
  TupleType t=new TupleType();
  for (  Node e : elts) {
    t.add(transformExpr(e,s));
  }
  return t;
}
