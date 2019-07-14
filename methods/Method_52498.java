private void rollupTypeUnary(TypeNode typeNode){
  if (typeNode.jjtGetNumChildren() >= 1) {
    Node child=typeNode.jjtGetChild(0);
    if (child instanceof TypeNode) {
      typeNode.setTypeDefinition(((TypeNode)child).getTypeDefinition());
    }
  }
}
