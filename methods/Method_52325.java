private ASTIfStatement findIfStatement(ASTBlock enclosingBlock,Node node){
  ASTIfStatement ifStatement=node.getFirstParentOfType(ASTIfStatement.class);
  List<ASTIfStatement> allIfStatements=enclosingBlock.findDescendantsOfType(ASTIfStatement.class);
  if (ifStatement != null && allIfStatements.contains(ifStatement)) {
    return ifStatement;
  }
  return null;
}
