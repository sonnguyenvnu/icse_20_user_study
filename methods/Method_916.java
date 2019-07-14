/** 
 * Order comments and expressions.
 * @param cUnit compilation unit
 * @return sorted comments and expressions
 */
protected SortedMap<Integer,Node> orderedCommentsAndExpressions(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=new TreeMap<>();
  List<ASTExpression> expressionNodes=cUnit.findDescendantsOfType(ASTExpression.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,expressionNodes);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,cUnit.getComments());
  return itemsByLineNumber;
}
