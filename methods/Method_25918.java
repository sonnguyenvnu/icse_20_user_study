/** 
 * Suggests removing getClass() or changing to Class.class. 
 */
@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (getClassMethodMatcher.matches(tree,state)) {
    String methodInvoker=state.getSourceForNode(ASTHelpers.getReceiver(tree));
    Fix removeGetClass=SuggestedFix.replace(tree,methodInvoker);
    Fix changeToClassDotClass=SuggestedFix.replace(tree,"Class.class");
    return buildDescription(tree).addFix(removeGetClass).addFix(changeToClassDotClass).build();
  }
  return Description.NO_MATCH;
}
