/** 
 * Checks whether this call is a call to  {@code TimeUnit.to*} and, if so, whether the units of itsparameter and its receiver disagree.
 */
private boolean checkTimeUnitToUnit(MethodInvocationTree tree,MethodSymbol methodSymbol,VisitorState state){
  if (tree.getMethodSelect().getKind() != MEMBER_SELECT) {
    return false;
  }
  MemberSelectTree memberSelect=(MemberSelectTree)tree.getMethodSelect();
  Symbol receiverSymbol=getSymbol(memberSelect.getExpression());
  if (receiverSymbol == null) {
    return false;
  }
  if (isTimeUnit(receiverSymbol,state) && receiverSymbol.isEnum() && TIME_UNIT_TO_UNIT_METHODS.containsValue(methodSymbol.getSimpleName().toString()) && tree.getArguments().size() == 1) {
    return check(receiverSymbol.getSimpleName().toString(),getOnlyElement(tree.getArguments()),state);
  }
  return false;
}
