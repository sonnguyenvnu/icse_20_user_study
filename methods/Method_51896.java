@Deprecated public int getArrayDepth(){
  if (jjtGetNumChildren() != 0 && (jjtGetChild(0) instanceof ASTReferenceType || jjtGetChild(0) instanceof ASTPrimitiveType)) {
    return ((Dimensionable)jjtGetChild(0)).getArrayDepth();
  }
  return 0;
}
