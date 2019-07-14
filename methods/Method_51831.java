public boolean isAnnotationMember(){
  return getNthParent(2) instanceof ASTAnnotationTypeBody;
}
