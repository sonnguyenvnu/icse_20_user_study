@Override public Object visitContinue(org.python.antlr.ast.Continue n) throws Exception {
  return new Continue(start(n),stop(n));
}
