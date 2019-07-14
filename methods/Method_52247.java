private boolean hasNameAsChild(Node node){
  if (node.jjtGetNumChildren() > 0) {
    if (node.jjtGetChild(0) instanceof ASTName) {
      return true;
    }
 else {
      return hasNameAsChild(node.jjtGetChild(0));
    }
  }
  return false;
}
