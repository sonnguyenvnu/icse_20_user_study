@Override public Value typecheck(Scope s){
  Value tv=typecheck(test,s);
  if (!(tv instanceof BoolType)) {
    _.abort(test,"test is not boolean: " + tv);
    return null;
  }
  Value type1=typecheck(then,s);
  Value type2=typecheck(orelse,s);
  return UnionType.union(type1,type2);
}
