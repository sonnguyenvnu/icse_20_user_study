@Override public Value typecheck(Scope s){
  s=new Scope(s);
  for (int i=0; i < statements.size() - 1; i++) {
    statements.get(i).typecheck(s);
  }
  return statements.get(statements.size() - 1).typecheck(s);
}
