/** 
 * Matches method invocations that return the same type as the receiver object. 
 */
private static Matcher<ExpressionTree> methodReturnsSameTypeAsReceiver(){
  return new Matcher<ExpressionTree>(){
    @Override public boolean matches(    ExpressionTree expressionTree,    VisitorState state){
      return isSameType(ASTHelpers.getReceiverType(expressionTree),ASTHelpers.getReturnType(expressionTree),state);
    }
  }
;
}
