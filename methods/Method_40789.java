public Value interp(Scope s){
  s=new Scope(s);
  for (int i=0; i < statements.size() - 1; i++) {
    statements.get(i).interp(s);
  }
  return statements.get(statements.size() - 1).interp(s);
}
