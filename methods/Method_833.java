/** 
 * Roll up the type based on type of the first child node.
 * @param typeNode type node
 */
private void rollupTypeUnary(TypeNode typeNode){
  Node node=typeNode;
  if (node.jjtGetNumChildren() >= 1) {
    Node child=node.jjtGetChild(0);
    if (child instanceof TypeNode) {
      typeNode.setType(((TypeNode)child).getType());
    }
  }
}
