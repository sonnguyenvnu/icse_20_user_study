@Override public String toString(){
  return "LocalScope:" + glomNames(getVariableDeclarations().keySet());
}
