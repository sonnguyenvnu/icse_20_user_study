private static boolean hasValidJavaTimeDurationOverload(MethodInvocationTree tree,VisitorState state){
  MethodSymbol methodSymbol=getSymbol(tree);
  if (methodSymbol == null) {
    return false;
  }
  Type durationType=state.getTypeFromString(JAVA_DURATION);
  Set<MethodSymbol> durationOverloads=ASTHelpers.findMatchingMethods(methodSymbol.name,input -> !input.equals(methodSymbol) && input.isStatic() == methodSymbol.isStatic() && input.getParameters().size() == 1 && isSameType(input.getParameters().get(0).asType(),durationType,state),ASTHelpers.enclosingClass(methodSymbol).asType(),state.getTypes());
  if (durationOverloads.isEmpty()) {
    return false;
  }
  MethodTree t=state.findEnclosing(MethodTree.class);
  if (t == null) {
    return true;
  }
  return durationOverloads.stream().noneMatch(getSymbol(t)::equals);
}
