private void checkBooleanMethods(ASTMethodDeclaration node,Object data,String nameOfMethod){
  ASTResultType resultType=node.getResultType();
  ASTType t=node.getResultType().getFirstChildOfType(ASTType.class);
  if (!resultType.isVoid() && t != null) {
    for (    String prefix : getProperty(BOOLEAN_METHOD_PREFIXES_PROPERTY)) {
      if (hasPrefix(nameOfMethod,prefix) && !isBooleanType(t)) {
        addViolationWithMessage(data,node,"Linguistics Antipattern - The method ''{0}'' indicates linguistically it returns a boolean, but it returns ''{1}''",new Object[]{nameOfMethod,t.getTypeImage()});
      }
    }
  }
}
