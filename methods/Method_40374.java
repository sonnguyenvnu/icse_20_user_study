@Override public Object visitImport(org.python.antlr.ast.Import n) throws Exception {
  List<Alias> aliases=new ArrayList<Alias>(n.getInternalNames().size());
  for (  alias e : n.getInternalNames()) {
    aliases.add(new Alias(e.getInternalName(),convertQname(e.getInternalNameNodes()),(Name)convExpr(e.getInternalAsnameNode()),start(e),stop(e)));
  }
  return new Import(aliases,start(n),stop(n));
}
