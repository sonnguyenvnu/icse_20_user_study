private boolean visit(AstNode node){
  if (parents.peek() == node) {
    return true;
  }
 else {
    build(node);
    return false;
  }
}
