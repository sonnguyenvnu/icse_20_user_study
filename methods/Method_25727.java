/** 
 * Wraps identity hashcode computations in calls to  {@link java.util.Arrays#hashCode} if the arrayis single dimensional or  {@link java.util.Arrays#deepHashCode} if the array ismultidimensional. <p>If there is only one argument to the hashcode method or the instance hashcode method is used, replaces the whole method invocation. If there are multiple arguments, wraps any that are of array type with the appropriate  {@link java.util.Arrays} hashcode method.
 */
@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  SuggestedFix.Builder fix=null;
  Types types=state.getTypes();
  if (jdk7HashCodeMethodMatcher.matches(tree,state)) {
    fix=SuggestedFix.builder().replace(tree,rewriteArrayArgument(tree.getArguments().get(0),state));
  }
 else   if (instanceHashCodeMethodMatcher.matches(tree,state)) {
    fix=SuggestedFix.builder().replace(tree,rewriteArrayArgument(((JCFieldAccess)tree.getMethodSelect()).getExpression(),state));
  }
 else   if (varargsHashCodeMethodMatcher.matches(tree,state)) {
    if (tree.getArguments().size() == 1) {
      ExpressionTree arg=tree.getArguments().get(0);
      Type elemType=types.elemtype(ASTHelpers.getType(arg));
      if (elemType.isPrimitive() || types.isArray(elemType)) {
        fix=SuggestedFix.builder().replace(tree,rewriteArrayArgument(arg,state));
      }
    }
 else {
      fix=SuggestedFix.builder();
      for (      ExpressionTree arg : tree.getArguments()) {
        if (types.isArray(ASTHelpers.getType(arg))) {
          fix.replace(arg,rewriteArrayArgument(arg,state));
        }
      }
    }
  }
  if (fix != null) {
    fix.addImport("java.util.Arrays");
    return describeMatch(tree,fix.build());
  }
  return Description.NO_MATCH;
}
