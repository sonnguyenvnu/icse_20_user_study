@Override public boolean visit(AstNode node){
  if (parents.peek() == node) {
    return true;
  }
 else {
    buildInternal(node);
    return false;
  }
}
