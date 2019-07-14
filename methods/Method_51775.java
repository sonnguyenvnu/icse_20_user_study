@Override public List<ASTAnnotation> getDeclaredAnnotations(){
  return this.jjtGetParent().findChildrenOfType(ASTAnnotation.class);
}
