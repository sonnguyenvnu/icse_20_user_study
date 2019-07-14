private void checkMethodOrConstructorComment(AbstractJavaAccessNode decl,Object data){
  if (decl.isPublic()) {
    checkCommentMeetsRequirement(data,decl,PUB_METHOD_CMT_REQUIREMENT_DESCRIPTOR);
  }
 else   if (decl.isProtected()) {
    checkCommentMeetsRequirement(data,decl,PROT_METHOD_CMT_REQUIREMENT_DESCRIPTOR);
  }
}
