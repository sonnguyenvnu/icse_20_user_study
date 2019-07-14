/** 
 * If the given expression is a call to a method checking the nullity of its first parameter, and otherwise returns that parameter.
 */
private static ExpressionTree stripNullCheck(ExpressionTree expression,VisitorState state){
  if (expression != null && expression.getKind() == METHOD_INVOCATION) {
    MethodInvocationTree methodInvocation=(MethodInvocationTree)expression;
    if (NON_NULL_MATCHER.matches(methodInvocation,state)) {
      return methodInvocation.getArguments().get(0);
    }
  }
  return expression;
}
