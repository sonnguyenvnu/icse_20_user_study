@Override public Object visitDictComp(org.python.antlr.ast.DictComp n) throws Exception {
  List<Comprehension> generators=new ArrayList<Comprehension>(n.getInternalGenerators().size());
  for (  comprehension c : n.getInternalGenerators()) {
    generators.add(new Comprehension(convExpr(c.getInternalTarget()),convExpr(c.getInternalIter()),convertListExpr(c.getInternalIfs()),start(c),stop(c)));
  }
  return new DictComp(convExpr(n.getInternalKey()),generators,start(n),stop(n));
}
