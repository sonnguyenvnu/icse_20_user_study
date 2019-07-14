private void checkGetters(ASTMethodDeclaration node,Object data,String nameOfMethod){
  ASTResultType resultType=node.getResultType();
  if (hasPrefix(nameOfMethod,"get") && resultType.isVoid()) {
    addViolationWithMessage(data,node,"Linguistics Antipattern - The getter ''{0}'' should not return void linguistically",new Object[]{nameOfMethod});
  }
}
