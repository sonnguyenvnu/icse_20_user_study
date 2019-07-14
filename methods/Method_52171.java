private void checkSetters(ASTMethodDeclaration node,Object data,String nameOfMethod){
  ASTResultType resultType=node.getResultType();
  if (hasPrefix(nameOfMethod,"set") && !resultType.isVoid()) {
    addViolationWithMessage(data,node,"Linguistics Antipattern - The setter ''{0}'' should not return any type except void linguistically",new Object[]{nameOfMethod});
  }
}
