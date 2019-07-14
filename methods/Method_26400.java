@Override public Description matchMethod(MethodTree tree,VisitorState state){
  if (tree.getThrows().isEmpty()) {
    return NO_MATCH;
  }
  List<ExpressionTree> uncheckedExceptions=new ArrayList<>();
  for (  ExpressionTree exception : tree.getThrows()) {
    Type exceptionType=getType(exception);
    if (isSubtype(exceptionType,state.getSymtab().runtimeExceptionType,state) || isSubtype(exceptionType,state.getSymtab().errorType,state)) {
      uncheckedExceptions.add(exception);
    }
  }
  if (uncheckedExceptions.isEmpty()) {
    return NO_MATCH;
  }
  return describeMatch(uncheckedExceptions.get(0),SuggestedFixes.deleteExceptions(tree,state,uncheckedExceptions));
}
