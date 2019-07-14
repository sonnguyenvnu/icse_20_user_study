@Override public Value typecheck(Scope s){
  return new Vector(typecheckList(elements,s));
}
