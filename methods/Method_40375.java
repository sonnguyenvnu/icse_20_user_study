@Override public Object visitImportFrom(org.python.antlr.ast.ImportFrom n) throws Exception {
  List<Alias> aliases=new ArrayList<Alias>(n.getInternalNames().size());
  for (  alias e : n.getInternalNames()) {
    aliases.add(new Alias(e.getInternalName(),convertQname(e.getInternalNameNodes()),(Name)convExpr(e.getInternalAsnameNode()),start(e),stop(e)));
  }
  return new ImportFrom(n.getInternalModule(),convertQname(n.getInternalModuleNames()),aliases,start(n),stop(n));
}
