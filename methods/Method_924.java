@Override public Object visit(ASTClassOrInterfaceDeclaration node,Object data){
  if (node.isInterface()) {
    return data;
  }
  try {
    List<Node> nodes=node.findChildNodesWithXPath(XPATH);
    for (    Node item : nodes) {
      if (!(item instanceof ASTName)) {
        continue;
      }
      List<ASTVariableDeclarator> parents=item.getParentsOfType(ASTVariableDeclarator.class);
      if (parents == null || parents.size() == 0 || parents.size() > 1) {
        continue;
      }
      ASTVariableDeclarator declarator=parents.get(0);
      ASTVariableDeclaratorId variableName=declarator.getFirstChildOfType(ASTVariableDeclaratorId.class);
      String valName=variableName.getImage();
      ASTBlock blockNode=variableName.getFirstParentOfType(ASTBlock.class);
      if (blockNode == null || valName == null) {
        continue;
      }
      List<Node> blockNodes=blockNode.findChildNodesWithXPath(CHILD_XPATH);
      for (      Node blockItem : blockNodes) {
        if (blockItem.getBeginLine() < item.getBeginLine()) {
          continue;
        }
        if (checkBlockNodesValid(valName,blockItem)) {
          addViolationWithMessage(data,blockItem,"java.set.UnsupportedExceptionWithModifyAsListRule.violation.msg",new Object[]{blockItem.getImage()});
        }
      }
    }
  }
 catch (  JaxenException e) {
    e.printStackTrace();
  }
  return super.visit(node,data);
}
