/** 
 * Find additive assignment with string literal, then check if the assigned variable defined out of the loop,
 * @param node
 * @param data
 * @param nodeClass
 */
private void checkStringConcat(Node node,Object data,Class nodeClass){
  try {
    List<? extends Node> additiveNodes=node.findChildNodesWithXPath(XPATH);
    for (    Node additiveNode : additiveNodes) {
      ASTAdditiveExpression additiveExpression=(ASTAdditiveExpression)additiveNode;
      Node assignmentStatement=additiveExpression.getNthParent(2);
      if (!(assignmentStatement instanceof ASTStatementExpression)) {
        continue;
      }
      List<Node> nodes=((ASTStatementExpression)assignmentStatement).findChildNodesWithXPath("PrimaryExpression/PrimaryPrefix/Name[@Image]");
      if (nodes == null || nodes.size() != NumberConstants.INTEGER_SIZE_OR_LENGTH_1) {
        continue;
      }
      NameDeclaration resultVar=((ASTName)nodes.get(0)).getNameDeclaration();
      if (resultVar != null && resultVar.getNode() != null) {
        boolean isDefinedInLoop=false;
        AbstractJavaNode loopStatement=(AbstractJavaNode)resultVar.getNode().getFirstParentOfType(nodeClass);
        while (loopStatement != null) {
          if (loopStatement == node) {
            isDefinedInLoop=true;
            break;
          }
          loopStatement=(AbstractJavaNode)loopStatement.getFirstParentOfType(nodeClass);
        }
        if (isDefinedInLoop) {
          return;
        }
      }
      for (int i=0; i < additiveNode.jjtGetNumChildren(); i++) {
        Node firstArg=additiveNode.jjtGetChild(i);
        if (!(firstArg instanceof ASTPrimaryExpression)) {
          continue;
        }
        List<Node> names=((ASTPrimaryExpression)firstArg).findChildNodesWithXPath("./PrimaryPrefix/Name[@Image]");
        if (names == null || names.size() != NumberConstants.INTEGER_SIZE_OR_LENGTH_1) {
          continue;
        }
        NameDeclaration firstArgVar=((ASTName)names.get(0)).getNameDeclaration();
        if (resultVar == firstArgVar) {
          addViolation(data,additiveNode);
          break;
        }
      }
    }
  }
 catch (  JaxenException e) {
    e.printStackTrace();
  }
}
