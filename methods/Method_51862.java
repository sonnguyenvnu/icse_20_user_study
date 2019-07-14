private int getArrayDimensionOnType(){
  ASTType typeNode=getTypeNode();
  if (typeNode != null) {
    return typeNode.getArrayDepth();
  }
  return 0;
}
