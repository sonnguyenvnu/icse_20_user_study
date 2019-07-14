/** 
 * Matches method calls whose receiver objects are of a type included in the set. 
 */
private static Matcher<ExpressionTree> methodReceiverHasType(final Set<String> typeSet){
  return new Matcher<ExpressionTree>(){
    @Override public boolean matches(    ExpressionTree expressionTree,    VisitorState state){
      Type receiverType=ASTHelpers.getReceiverType(expressionTree);
      return typeSet.contains(receiverType.toString());
    }
  }
;
}
