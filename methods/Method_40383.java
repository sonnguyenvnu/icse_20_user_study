@Override public Object visitPass(org.python.antlr.ast.Pass n) throws Exception {
  return new Pass(start(n),stop(n));
}
