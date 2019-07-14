/** 
 * Transforms a try-catch block in the try-fail pattern into a call to JUnit's  {@code assertThrows}, inserting the behavior of the  {@code try} block into a lambda parameter, andassigning the expected exception to a variable, if it is used within the  {@code catch} block.For example: <pre> try { foo(); fail(); } catch (MyException expected) { assertThat(expected).isEqualTo(other); } </pre> becomes <pre> {@code MyException expected = assertThrows(MyException.class, () -> foo());}assertThat(expected).isEqualTo(other); </pre>
 * @param tryTree the tree representing the try-catch block to be refactored.
 * @param throwingStatements the list of statements in the {@code throw} clause, <b>excluding</b>the fail statement.
 * @param state current visitor state (for source positions).
 * @return an {@link Optional} containing a {@link Fix} that replaces {@code tryTree} with anequivalent  {@code assertThrows}, if possible. Returns an  {@code Optional.empty()} if a fixcould not be constructed for the given code (e.g. multi-catch).
 */
public static Optional<Fix> tryFailToAssertThrows(TryTree tryTree,List<? extends StatementTree> throwingStatements,Optional<Tree> failureMessage,VisitorState state){
  List<? extends CatchTree> catchTrees=tryTree.getCatches();
  if (catchTrees.size() != 1) {
    return Optional.empty();
  }
  CatchTree catchTree=Iterables.getOnlyElement(catchTrees);
  SuggestedFix.Builder fix=SuggestedFix.builder();
  StringBuilder fixPrefix=new StringBuilder();
  if (throwingStatements.isEmpty()) {
    return Optional.empty();
  }
  List<? extends StatementTree> catchStatements=catchTree.getBlock().getStatements();
  fix.addStaticImport("org.junit.Assert.assertThrows");
  if (!catchStatements.isEmpty()) {
    fixPrefix.append(String.format("%s = ",state.getSourceForNode(catchTree.getParameter())));
  }
  fixPrefix.append(String.format("assertThrows(%s%s.class, () -> ",failureMessage.filter(t -> ASTHelpers.constValue(t,String.class) == null).map(t -> state.getSourceForNode(t) + ", ").orElse(""),state.getSourceForNode(catchTree.getParameter().getType())));
  boolean useExpressionLambda=throwingStatements.size() == 1 && getOnlyElement(throwingStatements).getKind() == Kind.EXPRESSION_STATEMENT;
  if (!useExpressionLambda) {
    fixPrefix.append("{");
  }
  fix.replace(((JCTree)tryTree).getStartPosition(),((JCTree)throwingStatements.iterator().next()).getStartPosition(),fixPrefix.toString());
  if (useExpressionLambda) {
    fix.postfixWith(((ExpressionStatementTree)throwingStatements.iterator().next()).getExpression(),")");
  }
 else {
    fix.postfixWith(getLast(throwingStatements),"});");
  }
  if (catchStatements.isEmpty()) {
    fix.replace(state.getEndPosition(getLast(throwingStatements)),state.getEndPosition(tryTree),"");
  }
 else {
    fix.replace(state.getEndPosition(getLast(throwingStatements)),((JCTree)catchStatements.get(0)).getStartPosition(),"\n");
    fix.replace(state.getEndPosition(getLast(catchStatements)),state.getEndPosition(tryTree),"");
  }
  return Optional.of(fix.build());
}
