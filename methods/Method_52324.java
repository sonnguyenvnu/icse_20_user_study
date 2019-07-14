private boolean variableIsPassedToMethod(ASTPrimaryExpression expr,String variable){
  List<ASTName> methodParams=new ArrayList<>();
  expr.findDescendantsOfType(ASTName.class,methodParams,true);
  for (  ASTName pName : methodParams) {
    String paramName=pName.getImage();
    ASTArgumentList parentParam=pName.getFirstParentOfType(ASTArgumentList.class);
    if (paramName.equals(variable) && parentParam != null) {
      return true;
    }
  }
  return false;
}
