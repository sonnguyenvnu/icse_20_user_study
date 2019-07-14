@Override public Description matchMethod(MethodTree methodTree,VisitorState state){
  MethodSymbol methodSymbol=ASTHelpers.getSymbol(methodTree);
  boolean isVarargs=(methodSymbol.flags() & Flags.VARARGS) != 0;
  Set<MethodSymbol> superMethods=ASTHelpers.findSuperMethods(methodSymbol,state.getTypes());
  if (superMethods.isEmpty()) {
    return Description.NO_MATCH;
  }
  Iterator<MethodSymbol> superMethodsIterator=superMethods.iterator();
  boolean areSupersVarargs=superMethodsIterator.next().isVarArgs();
  while (superMethodsIterator.hasNext()) {
    if (areSupersVarargs != superMethodsIterator.next().isVarArgs()) {
      return describeMatch(methodTree);
    }
  }
  if (isVarargs == areSupersVarargs) {
    return Description.NO_MATCH;
  }
  List<? extends VariableTree> parameterTree=methodTree.getParameters();
  Tree paramType=parameterTree.get(parameterTree.size() - 1).getType();
  CharSequence paramTypeSource=state.getSourceForNode(paramType);
  if (paramTypeSource == null) {
    return describeMatch(methodTree);
  }
  Description.Builder descriptionBuilder=buildDescription(methodTree);
  if (isVarargs) {
    descriptionBuilder.addFix(SuggestedFix.replace(paramType,"[]",paramTypeSource.length() - 3,0));
  }
 else {
    int arrayOpenIndex=paramTypeSource.length() - 2;
    while (paramTypeSource.charAt(arrayOpenIndex) == ' ') {
      arrayOpenIndex--;
    }
    if (paramTypeSource.charAt(arrayOpenIndex) == '[') {
      descriptionBuilder.addFix(SuggestedFix.replace(paramType,"...",arrayOpenIndex,0));
    }
  }
  return descriptionBuilder.build();
}
