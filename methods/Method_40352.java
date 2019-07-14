private Module generateAST(mod ast,String path) throws Exception {
  if (ast == null) {
    Indexer.idx.reportFailedAssertion("ANTLR returned NULL for " + path);
    return null;
  }
  Object obj=ast.accept(new AstConverter());
  if (!(obj instanceof Module)) {
    warn("\n[warning] converted AST is not a module: " + obj);
    return null;
  }
  Module module=(Module)obj;
  if (new File(path).canRead()) {
    module.setFile(path);
  }
  return module;
}
