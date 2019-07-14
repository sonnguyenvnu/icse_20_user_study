/** 
 * Search the list of thrown exceptions for Exception
 */
private void checkExceptions(Node method,Object o){
  List<ASTName> exceptionList=Collections.emptyList();
  ASTNameList nameList=method.getFirstChildOfType(ASTNameList.class);
  if (nameList != null) {
    exceptionList=nameList.findDescendantsOfType(ASTName.class);
  }
  if (!exceptionList.isEmpty()) {
    evaluateExceptions(exceptionList,o);
  }
}
