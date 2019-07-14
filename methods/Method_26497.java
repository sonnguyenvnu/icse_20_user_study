@Override public Description matchMethod(MethodTree methodTree,VisitorState state){
  MethodSymbol methodSymbol=ASTHelpers.getSymbol(methodTree);
  if (isSynchronized(methodSymbol)) {
    return NO_MATCH;
  }
  for (  MethodSymbol s : ASTHelpers.findSuperMethods(methodSymbol,state.getTypes())) {
    if (isSynchronized(s)) {
      if (isSameType(s.owner.type,state.getTypeFromString("java.io.InputStream"),state)) {
        continue;
      }
      if (ignore(methodTree,state)) {
        return NO_MATCH;
      }
      return buildDescription(methodTree).addFix(SuggestedFixes.addModifiers(methodTree,state,Modifier.SYNCHRONIZED)).setMessage(String.format("Unsynchronized method %s overrides synchronized method in %s",methodSymbol.getSimpleName(),s.enclClass().getSimpleName())).build();
    }
  }
  return NO_MATCH;
}
