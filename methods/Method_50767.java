/** 
 * Check if class contains any Database.query / Database.insert [ Database. ] methods
 * @param node
 * @param data
 */
private void checkForDatabaseMethods(ASTUserClass node,Object data,boolean sharingFound){
  List<ASTMethodCallExpression> calls=node.findDescendantsOfType(ASTMethodCallExpression.class);
  for (  ASTMethodCallExpression call : calls) {
    if (Helper.isMethodName(call,"Database",Helper.ANY_METHOD)) {
      if (!sharingFound) {
        reportViolation(node,data);
      }
    }
  }
}
