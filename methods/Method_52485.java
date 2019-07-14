public boolean isPrimitiveType(){
  return !isTypeInferred() && getAccessNodeParent().getFirstChildOfType(ASTType.class).jjtGetChild(0) instanceof ASTPrimitiveType;
}
