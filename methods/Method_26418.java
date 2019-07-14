@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!MATCHER.matches(tree,state)) {
    return Description.NO_MATCH;
  }
  Tree receiverOfConversion=ASTHelpers.getReceiver(tree);
  if (receiverOfConversion == null) {
    return Description.NO_MATCH;
  }
  String timeUnitName=ASTHelpers.getSymbol(receiverOfConversion).getSimpleName().toString();
  Optional<TimeUnit> receiver=Enums.getIfPresent(TimeUnit.class,timeUnitName);
  if (!receiver.isPresent()) {
    return Description.NO_MATCH;
  }
  String methodName=ASTHelpers.getSymbol(tree).getSimpleName().toString();
  TimeUnit convertTo=methodNameToTimeUnit(methodName);
  ExpressionTree arg0=tree.getArguments().get(0);
  Long constant=Longs.tryParse(String.valueOf(state.getSourceForNode(arg0)));
  if (constant != null) {
    long converted=invokeConversion(receiver.get(),methodName,constant);
    if (converted == 0 || converted == 1 || constant == converted) {
      SuggestedFix fix=replaceTreeWith(tree,convertTo,converted + "L");
      return describeMatch(tree,fix);
    }
    if (receiver.get().compareTo(convertTo) < 0) {
      return describeMatch(tree);
    }
  }
  if (receiver.get().equals(convertTo)) {
    SuggestedFix fix=replaceTreeWith(tree,convertTo,state.getSourceForNode(arg0));
    return describeMatch(tree,fix);
  }
  return Description.NO_MATCH;
}
