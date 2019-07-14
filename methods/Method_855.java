/** 
 * Check comments behind nodes.
 * @param cUnit compilation unit
 * @return sorted comments and expressions
 */
protected SortedMap<Integer,Node> orderedCommentsAndExpressions(ASTCompilationUnit cUnit){
  SortedMap<Integer,Node> itemsByLineNumber=new TreeMap<>();
  List<ASTExpression> expressionNodes=cUnit.findDescendantsOfType(ASTExpression.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,expressionNodes);
  List<ASTFieldDeclaration> fieldNodes=cUnit.findDescendantsOfType(ASTFieldDeclaration.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,fieldNodes);
  List<ASTEnumConstant> enumConstantNodes=cUnit.findDescendantsOfType(ASTEnumConstant.class);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,enumConstantNodes);
  NodeSortUtils.addNodesToSortedMap(itemsByLineNumber,cUnit.getComments());
  return itemsByLineNumber;
}
