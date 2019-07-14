private void buildFormalComment(AstNode node){
  if (parents.peek() == node) {
    ApexNode<?> parent=(ApexNode<?>)nodes.peek();
    assignApexDocTokenToNode(node,parent);
  }
}
