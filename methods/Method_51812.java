public boolean isAnonymousClass(){
  return jjtGetParent().getFirstChildOfType(ASTClassOrInterfaceBody.class) != null;
}
