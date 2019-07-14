private boolean hasLombokNoArgsConstructor(ASTClassOrInterfaceDeclaration parent){
  ASTAnnotation annotation=parent.getAnnotation("lombok.NoArgsConstructor");
  if (annotation != null) {
    List<ASTMemberValuePair> memberValuePairs=annotation.findDescendantsOfType(ASTMemberValuePair.class);
    for (    ASTMemberValuePair memberValuePair : memberValuePairs) {
      if ("access".equals(memberValuePair.getImage())) {
        List<ASTName> names=memberValuePair.findDescendantsOfType(ASTName.class);
        for (        ASTName name : names) {
          if (name.getImage().endsWith("PRIVATE")) {
            return true;
          }
        }
      }
    }
  }
  return false;
}
