private void checkPrefixedTransformMethods(ASTMethodDeclaration node,Object data,String nameOfMethod){
  ASTResultType resultType=node.getResultType();
  List<String> prefixes=getProperty(TRANSFORM_METHOD_NAMES_PROPERTY);
  String[] splitMethodName=StringUtils.splitByCharacterTypeCamelCase(nameOfMethod);
  if (resultType.isVoid() && splitMethodName.length > 0 && prefixes.contains(splitMethodName[0].toLowerCase(Locale.ROOT))) {
    addViolationWithMessage(data,node,"Linguistics Antipattern - The transform method ''{0}'' should not return void linguistically",new Object[]{nameOfMethod});
  }
}
