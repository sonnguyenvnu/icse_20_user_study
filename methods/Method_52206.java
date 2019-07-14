/** 
 * Determine if the given return statement has any embedded method calls.
 * @param rtn return statement to analyze
 * @return true if any method calls are made within the given return
 */
private boolean isMethodCall(ASTReturnStatement rtn){
  List<ASTPrimarySuffix> suffix=rtn.findDescendantsOfType(ASTPrimarySuffix.class);
  for (  ASTPrimarySuffix element : suffix) {
    if (element.isArguments()) {
      return true;
    }
  }
  return false;
}
