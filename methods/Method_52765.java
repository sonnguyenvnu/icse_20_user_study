public ASTDatatype getTypeNode(){
  for (int i=0; i < jjtGetNumChildren(); i++) {
    if (jjtGetChild(i) instanceof ASTDatatype) {
      return (ASTDatatype)jjtGetChild(i);
    }
  }
  throw new IllegalStateException("ASTType not found");
}
