@Override public Object visitListComp(org.python.antlr.ast.ListComp n) throws Exception {
  List<Comprehension> generators=new ArrayList<Comprehension>(n.getInternalGenerators().size());
  for (  comprehension c : n.getInternalGenerators()) {
    generators.add(new Comprehension(convExpr(c.getInternalTarget()),convExpr(c.getInternalIter()),convertListExpr(c.getInternalIfs()),start(c),stop(c)));
  }
  return new ListComp(convExpr(n.getInternalElt()),generators,start(n),stop(n));
}
