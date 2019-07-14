protected static ASTNode findClosestNode(int lineNumber,ASTNode node){
  log("findClosestNode to line " + lineNumber);
  ASTNode parent=findClosestParentNode(lineNumber,node);
  log("findClosestParentNode returned " + getNodeAsString(parent));
  if (parent == null)   return null;
  if (getLineNumber(parent) == lineNumber) {
    log(parent + "|PNode " + getLineNumber(parent) + ", lfor " + lineNumber);
    return parent;
  }
  List<ASTNode> nodes;
  if (parent instanceof TypeDeclaration) {
    nodes=((TypeDeclaration)parent).bodyDeclarations();
  }
 else   if (parent instanceof Block) {
    nodes=((Block)parent).statements();
  }
 else {
    log("findClosestNode() found " + getNodeAsString(parent));
    return null;
  }
  if (nodes.size() > 0) {
    ASTNode retNode=parent;
    for (    ASTNode cNode : nodes) {
      log(cNode + "|cNode " + getLineNumber(cNode) + ", lfor " + lineNumber);
      if (getLineNumber(cNode) <= lineNumber)       retNode=cNode;
    }
    return retNode;
  }
  return parent;
}
