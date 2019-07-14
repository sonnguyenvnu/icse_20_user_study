private void checkTransformMethods(ASTMethodDeclaration node,Object data,String nameOfMethod){
  ASTResultType resultType=node.getResultType();
  List<String> infixes=getProperty(TRANSFORM_METHOD_NAMES_PROPERTY);
  for (  String infix : infixes) {
    if (resultType.isVoid() && containsWord(nameOfMethod,StringUtils.capitalize(infix))) {
      addViolationWithMessage(data,node,"Linguistics Antipattern - The transform method ''{0}'' should not return void linguistically",new Object[]{nameOfMethod});
      break;
    }
  }
}
