private static Optional<Fix> buildFix(JCTry tryStatement,MethodInvocationTree tree,VisitorState state){
  if (!ASTHelpers.getSymbol(tree).getSimpleName().contentEquals("fail")) {
    return Optional.empty();
  }
  JCBlock block=tryStatement.getBlock();
  if (!expressionStatement((t,s) -> t.equals(tree)).matches(getLast(block.getStatements()),state)) {
    return Optional.empty();
  }
  if (tryStatement.getCatches().size() != 1) {
    return Optional.empty();
  }
  JCCatch catchTree=Iterables.getOnlyElement(tryStatement.getCatches());
  if (catchTree.getParameter().getType().getKind() == Kind.UNION_TYPE) {
    return Optional.empty();
  }
  SuggestedFix.Builder fix=SuggestedFix.builder();
  boolean expression=block.getStatements().size() == 2 && block.getStatements().get(0).getKind() == Kind.EXPRESSION_STATEMENT;
  int startPosition;
  int endPosition;
  if (expression) {
    JCExpressionStatement expressionTree=(JCExpressionStatement)block.getStatements().get(0);
    startPosition=expressionTree.getStartPosition();
    endPosition=state.getEndPosition(expressionTree.getExpression());
  }
 else {
    startPosition=block.getStartPosition();
    endPosition=getLast(tryStatement.getBlock().getStatements()).getStartPosition();
  }
  if (catchTree.getBlock().getStatements().isEmpty()) {
    fix.addStaticImport("org.junit.Assert.assertThrows");
    fix.replace(tryStatement.getStartPosition(),startPosition,String.format("assertThrows(%s.class, () -> ",state.getSourceForNode(catchTree.getParameter().getType()))).replace(endPosition,state.getEndPosition(catchTree),(expression ? "" : "}") + ");\n");
  }
 else {
    fix.addStaticImport("org.junit.Assert.assertThrows").prefixWith(tryStatement,state.getSourceForNode(catchTree.getParameter())).replace(tryStatement.getStartPosition(),startPosition,String.format(" = assertThrows(%s.class, () -> ",state.getSourceForNode(catchTree.getParameter().getType()))).replace(endPosition,catchTree.getBlock().getStatements().get(0).getStartPosition(),(expression ? "" : "}") + ");\n").replace(state.getEndPosition(getLast(catchTree.getBlock().getStatements())),state.getEndPosition(catchTree),"");
  }
  return Optional.of(fix.build());
}
