@Override public Object visitStr(org.python.antlr.ast.Str n) throws Exception {
  return new Str(n.getInternalS(),start(n),stop(n));
}
